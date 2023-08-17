<!DOCTYPE html>
<%@page import="java.time.Period"%>
<%@page import="dto.Task"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>
</head>
<body>
	<%
	Task task=(Task) request.getAttribute("task");
	%>
	<h1>Enter Task</h1>
	<form action="updatetask" method="post">
		<input type="text" name="id" value="<%=task.getId() %>" hidden="true" required/th>
		<fieldset>
			<table border="1">
				
				<tr>
					<th>Task Name:</th>
					<td><input type="text" name="name" value="<%=task.getName() %>" required="required"></td>
				</tr>
				<tr>
					<th>Task Description:</th>
					<td><input type="text" name="description" value="<%=task.getDescription() %>" required="required"></td>
				</tr>
				<tr>
					<th>Number of days <br> required to <br> complete task</th>
					<td><input type="text" name="days" value="<%=Period.between(task.getTaskdate(), task.getCompletiondate()).getDays() %>" required="required"></td>
				</tr>
				<tr>
					<td><button>Update</button></td>
					<td><button type="reset">Cancel</button></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
	<a href="backtohome"><button>BACK</button></a>
</body>
</html>