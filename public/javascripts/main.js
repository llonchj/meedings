$(document).ready(function() {
  $("input[type='checkbox']").bind('click', function() {
	var action = #{jsAction @attend(':id') /}
    $.post(action({ id: ${participant.id} }),
      function(data) {
    });
  });
});
