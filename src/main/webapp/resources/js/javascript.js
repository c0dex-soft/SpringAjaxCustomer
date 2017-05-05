$(document).ready(function() {
    
	function deleteCustomer(id) {
		console.log("Kliknuto je na dugme DELETE za red sa ID=" + id);

		$.ajax({
			type : "POST",
			url : '/spring-ajax-customer/customer?del=true&&id=' + id,
			dataType : 'text',
			success : function(result) {
				console.log("Podatak " + result + " je vracen");
				$('#tr_' + result).remove();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Customer " + textStatus + " " + errorThrown + " !");
			}
		});
	}

	$('.delete').click(function(e) {

		/*
		 * $.post('/spring-ajax-customer/customer?del=true&&id=' +id,
		 * $(this).serialize, function(result) { console.log("Podatak je
		 * vracen"); // console.log("tr_Id -> " +result); //
		 * $('#tr_'+result).remove(); });
		 */

		var r = confirm("Do you really want to delete customer?");
		if (r == true) {
			var strId = $(this).parent().parent().attr("id");
			var id = strId.substr(3);
            deleteCustomer(id);
		}

		e.preventDefault;

	});
	
	$("#dobInput").datepicker({
		firstDay : 1,

		showButtonPanel : true,
		currentText : "Today",
		closeText : "Close",

		constrainInput : true,
		dateFormat: "dd-mm-yy",

		changeMonth : true,
		changeYear : true

	});

});