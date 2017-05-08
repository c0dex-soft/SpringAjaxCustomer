$(document).ready(function () {
    
	var table = $('#tbody').html();

    $('#saveCustomer').submit(function (e) {
        e.preventDefault();
    	
        $.post('/spring-ajax-customer/add', $('#saveCustomer').serialize(), function (customer) {
            $('#tbody').last().append(
                '<tr id="tr_' +customer.id+ '">'+
                    '<td>' +customer.id+ '</td>'+
                    '<td>' +customer.firstName+ '</td>'+
                    '<td>' +customer.lastName+ '</td>'+
                    '<td>' +customer.email+ '</td>'+
                    '<td>' +customer.phoneNumber+ '</td>'+
                    '<td id="dob_' +customer.id+ '" class="date" >' +customer.dob+ '</td>'+
                    '<td><button class="edit" type="button">Edit</button></td>'+
                    '<td><input class="delete" type="button" value="Delete" /></td>'+
				'</tr>'
            );
            document.location.reload();   
        });
        
        clearInputs();
    });
    

	function clearInputs() {
	    $('input[id*="Input"]').each(function () {
	    	if ($(this).attr('id', 'dobInput')) {
	    		$(this).css('background-color', '#faffbd');
	    	}
            $(this).css('autofill', 'white');
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
	

	
	$("#container #saveCustomer #dobInput, #dialog_dob").datepicker({
		firstDay : 1,
		yearRange: "-100:+0",
		showButtonPanel : true,
		currentText : "Today",
		closeText : "Close",

		constrainInput : true,
		dateFormat: "dd-mm-yy",
		maxDate: "today",

		changeMonth : true,
		changeYear : true

	});
	
    $('#search').focusout(function() {
    	document.location.reload(); 
    });
	
	$('#search').keyup(function (e) {
	        var filter = $('#search').val();
	        console.log(table);
	
	        if (filter.length > 0) {
	        	$('tr[id*="tr_"]').remove();
	        	 console.log(table);
	            loadTable(filter);
	        } else {
	            $('tr[id*="tr_"]').remove();
	            $('#tbody').last().append(table);
	        }
	});
	 
	function loadTable(filter) {
		    var url = "/spring-ajax-customer/search/" +filter;
	
	    $('#tbody').load(url, function (response, status, xhr) {
	        if (status == "error") {
	            var msg = "Sorry but there was an error: ";
	            $('#info').html(msg + xhr.status + " " +xhr.statusText);
	        }
	    });
	
	    return false;
	}
	
	$("#dialog-form").dialog({
	      autoOpen: false,
	      height: 370,
	      width: 550,
	      buttons: [
	    	  {
	    		  text: "Update",
	    		  click: function() {
	    			  $.post('/spring-ajax-customer/update?id=' +$("#dialog_id").text(), $('#update_form').serialize(), function (customer) {  			  			
	    				$("#notification").html("<h2>DETAILS WAS UPDATED</h2>").css("color", "green");
	    				console.log("Customer: " +customer.id+" | "+customer.firstName+" | "+customer.lastName+" | "+customer.email+" | "+customer.phoneNumber+" | "+customer.dob);  
	    			  })
	    			  	.fail(function() {
	    			  		$("#notification").html("<h2>ERROR OCCURRED - DETAILS WASN'T UPDATED</h2>").css("color", "RED");
	    			  	});
	    		  }
	    	  },
	    	  {
	    		  text: "Cancel",
	    		  click: function() {
	    			  document.location.reload(); 
	    			  $(this).dialog("close");
	    		  }
	    	  }
	      ],
	      close: function() {
	    	  document.location.reload(); 
			  $(this).dialog("close");
	      }
	    });
	
	$(".edit").click(function() {
		var tr = $(this).parent().parent();
		var id, fname, lname, email, phone, dob;
        tr.each(function( i ) {
            $("td", this).each(function( j ) {
                switch(j) {
                    case 0: id = $(this).text();
                            break;
                    case 1: fname = $(this).text();
                            break;
                    case 2: lname = $(this).text();
                            break;
                    case 3: email = $(this).text();
                            break;
                    case 4: phone = $(this).text();
                            break;
                    case 5: dob = $(this).text();
                            break;
                }
            });
//            console.log("ID: "+id+" / First Name: "+fname+" / Last Name: "+lname+" / Email: "+email+" / Phone: "+phone+" / DoB: "+dob);
            $("#dialog_id").text(id);
            $("#dialog_firstName").val(fname);
            $("#dialog_lastName").val(lname);
            $("#dialog_email").val(email);
            $("#dialog_phoneNumber").val(phone);
            $("#dialog_dob").val(dob);
        });
		$("#dialog-form").dialog("open");
	})
	 

});