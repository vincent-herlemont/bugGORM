<%@ page import="buggorm.Obj2" %>



<div class="fieldcontain ${hasErrors(bean: obj2Instance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="obj2.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${obj2Instance?.name}"/>

</div>

