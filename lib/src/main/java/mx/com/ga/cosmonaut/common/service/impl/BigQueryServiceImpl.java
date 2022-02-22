package mx.com.ga.cosmonaut.common.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import mx.com.ga.cosmonaut.common.dto.BigQueryConfiguracionDto;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.BigQueryService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.*;

@Singleton
public class BigQueryServiceImpl implements BigQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(BigQueryServiceImpl.class);

    private BigQuery bigQuery = null;

    private BigQueryConfiguracionDto bigQueryConfig;

    @Override
    @PostConstruct
    public void inicializar() throws ServiceException {
        Credentials credenciales;
        try {
            bigQueryConfig = new BigQueryConfiguracionDto();
            bigQueryConfig.setIdProyecto(Constantes.GOOGLE_STORAGE_ID_PROYECTO);
            bigQueryConfig.setRutaJson(Constantes.GOOGLE_STORAGE_JSON);
            credenciales = GoogleCredentials.fromStream(this.getClass().getResourceAsStream(bigQueryConfig.getRutaJson()));
            bigQuery = BigQueryOptions.newBuilder().setCredentials(credenciales)
                    .setProjectId(bigQueryConfig.getIdProyecto()).build().getService();
        } catch (IOException e) {
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Override
    public void crearTablas() {
        String datasetName = "MY_DATASET_NAME";
        String tableName = "MY_TABLE_NAME";
        Schema schema = Schema.of(Field.of("stringField", StandardSQLTypeName.STRING),
                Field.of("booleanField", StandardSQLTypeName.BOOL),
                Field.of("numericField", StandardSQLTypeName.NUMERIC));

        try {
            TableId tableId = TableId.of(datasetName, tableName);
            TableDefinition tableDefinition = StandardTableDefinition.of(schema);
            TableInfo tableInfo = TableInfo.newBuilder(tableId, tableDefinition).build();

            bigQuery.create(tableInfo);
        } catch (BigQueryException e) {
            LOG.error("Table was not created.", e);
        }
    }

    @Override
    public void insertarRegistros() {
        String datasetName = "MY_DATASET_NAME";
        String tableName = "MY_TABLE_NAME";

        /**INFO A INSERTAR*/
        Map<String, Object> rowContent = new HashMap<>();
        rowContent.put("stringField", "Value of Pi");
        rowContent.put("booleanField", true);
        rowContent.put("numericField", "3.14");

        try {
            TableId tableId = TableId.of(datasetName, tableName);
            InsertAllResponse response = bigQuery.insertAll(InsertAllRequest.newBuilder(tableId)
                    .addRow(rowContent)
                    .build());

            if (response.hasErrors()) {
                /** Si falla una insercion, te deja ver los errores (No transactional) */
                for (Map.Entry<Long, List<BigQueryError>> entry : response.getInsertErrors().entrySet()) {
                    LOG.error("Response error: {0}", entry.getValue());
                }
            }
            LOG.info("Rows successfully inserted into table.");
        } catch (BigQueryException e) {
            LOG.error("Insert operation not performed.", e);
        }
    }

    @Override
    public List<String> consultaCommits() {
        try {
            TableResult result = doQuery("SELECT commit, author, repo_name "
                    + "FROM `bigquery-public-data.github_repos.commits` WHERE subject like '%bigquery%' "
                    + "ORDER BY subject DESC LIMIT 10");
            if (result != null) {
                /** Itera los resultados */
                List<String> emails = new ArrayList<>();
                for (FieldValueList row : result.iterateAll()) {
                    String commit = row.get("commit").getStringValue();

                    /** Info del autor */
                    FieldValueList author = row.get("author").getRecordValue();
                    String name = author.get("name").getStringValue();
                    String email = author.get("email").getStringValue();

                    LOG.error("commit: {0}, nombreAutor: {1}, email:{2}",commit, name, email);
                    emails.add(email);
                }
                return emails;
            } else {
                return null;
            }
        } catch (InterruptedException ex) {
            return null;
        }
    }

    private TableResult doQuery(String sql) throws InterruptedException {
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql)
                .setUseLegacySql(false)
                .build();

        /** Crea el Job con un ID especifico y espera la respuesta*/
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
        queryJob = queryJob.waitFor();

        if (queryJob != null && queryJob.getStatus().getError() == null) {
            /** Obtiene el result */
            return queryJob.getQueryResults();
        } else {
            return null;
        }
    }

}
