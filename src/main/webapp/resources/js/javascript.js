$(document).ready(function () {
    
/*	$('#searchText').on("click", function(){
		$("#searchInput").toggle("slide", 500);
	});*/

    $('#saveCustomer').submit(function (e) {
        e.preventDefault();
    	
        var formData = $('#saveCustomer').serialize();
        console.log(formData);
    	
        $.post('/spring-ajax-customer/add', $('#saveCustomer').serialize(), function (customer) {
            $('#customerTableResponse').last().append(
                '<tr id="tr_' +customer.id+ '">'+
                    '<td>' +customer.id+ '</td>'+
                    '<td>' +customer.firstName+ '</td>'+
                    '<td>' +customer.lastName+ '</td>'+
                    '<td>' +customer.email+ '</td>'+
                    '<td>' +customer.phoneNumber+ '</td>'+
                    '<td id="dob_' +customer.id+ '" class="date" >' +customer.dob+ '</td>'+
                    '<td><input class="edit" type="button" value="Edit" /></td>'+
                    '<td><input class="delete" type="button" value="Delete" /></td>'+
				'</tr>'
            );
        });
 
        clearInputs();
    })

	function clearInputs() {
	    $('input[id*="Input"]').each(function () {
	        $(this).val('');
	    });
	}
    

	function deleteCustomer(id) {
		console.log("Kliknuto je na dugme DELETE za red sa ID=" + id);

		$.ajax({
			type : "POST",
			url : '/spring-ajax-customer/remove?id=' + id,
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
	
	$( "tr td input" ).on( "click", ".delete", function() {
		  console.log("KLIKNUTO");
		});

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

		e.preventDefault();

	});
	
/*	$("#dobInput").datepicker({
		firstDay : 1,

		showButtonPanel : true,
		currentText : "Today",
		closeText : "Close",

		constrainInput : true,
		dateFormat: "dd-mm-yy",

		changeMonth : true,
		changeYear : true

	});*/

});