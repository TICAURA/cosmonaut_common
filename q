[1mdiff --git a/lib/src/main/java/mx/com/ga/cosmonaut/common/repository/administracion/usuarios/AdmRolesRepository.java b/lib/src/main/java/mx/com/ga/cosmonaut/common/repository/administracion/usuarios/AdmRolesRepository.java[m
[1mindex cc4a252..23a7ac6 100644[m
[1m--- a/lib/src/main/java/mx/com/ga/cosmonaut/common/repository/administracion/usuarios/AdmRolesRepository.java[m
[1m+++ b/lib/src/main/java/mx/com/ga/cosmonaut/common/repository/administracion/usuarios/AdmRolesRepository.java[m
[36m@@ -18,7 +18,6 @@[m [mpublic interface AdmRolesRepository extends CrudRepository<AdmRoles, Integer> {[m
 [m
     List<AdmRoles> findByEsActivoOrderByNombreRol(Boolean activo);[m
 [m
[31m-    @Join(value = "centrocClientes", )[m
     List<AdmRoles> findByCentrocClientesCentrocClienteIdAndEsActivo(Integer centrocClienteId, boolean esActivo);[m
 [m
 }[m
