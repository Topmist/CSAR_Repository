<#import "layout.ftl" as layout>
<@layout.sb_admin>

<div class="row">
    <div class="col-lg-12">

<h2>CsarFile-Details</h2>

<form action="${basePath}/uploadcsarfile?csarId=${csar.id}" method="post" enctype="multipart/form-data">
	<input type="file" name="csarFile" />
	<input type="submit" value="Upload" />
</form>

<table id="example" class="table table-striped table-bordered" border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Version</th>
			<th>upload date</th>
			<th>Size</th>
			<th>fileName</th>
			<th>hash</th>
			<th>download</th>
			<th>delete</th>
		</tr>
	</thead>
	<tbody>
<#list csarFiles as csarFile>
	<tr>
		<td>${csarFile.id}:</td> 
		<td>${csarFile.name}</td>
		<td>${csarFile.version}</td>
		<td>${csarFile.uploadDate}</td>
		<td>${csarFile.hashedFile.size}</td>
		<td>${csarFile.hashedFile.fileName}</td>
		<td>${csarFile.hashedFile.hash}</td>
		<td><a href="${basePath}/downloadcsarfile?csarfileid=${csarFile.hashedFile.fileName?remove_ending(".csar")}">Download</a></td>
		<td><a href="${basePath}/deletecsarfile?csarfileid=${csarFile.id}">Delete</a></td>
	</tr>
</#list>
</tbody>
</table>
    </div>
    <!-- /.col-lg-12 -->
</div>

<script>
${r"$(document).ready(function() {
    $('#example').dataTable();
} );"}
</script>

<link rel="stylesheet" href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css">
<script src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
</@layout.sb_admin>