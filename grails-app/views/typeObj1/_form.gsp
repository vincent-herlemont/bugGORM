<%@ page import="buggorm.TypeObj1" %>



<div class="fieldcontain ${hasErrors(bean: typeObj1Instance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="typeObj1.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${typeObj1Instance?.name}"/>

</div>

