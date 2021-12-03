	function checkIsNull(value) {
		return value === "" || value === undefined || value === null ? true : false;
	}
	function loginUser() {
		let requestBody = {
			"emailId": $("#emailId").val(),
			"password": $("#pwdId").val()
		}
		if (checkIsNull($("#emailId").val()) || checkIsNull($("#pwdId").val())) {
			alert("Please fill Required Data");
		} else {
			$.ajax({
				type: 'post',
				contentType: "application/json",
       			dataType: 'json',
        		cache: false,
				url: "http://localhost:8090/login",
				data: JSON.stringify(requestBody),
				success: function(lresponse) {
					if (typeof (Storage) !== "undefined") {
						// Store
						localStorage.setItem("patientId", lresponse.patientId);
						window.location = "/home";
					}
				}, error: function(error) {
					alert("Something went wrong");
				}
			});
		}
	}
	function registerUser() {

		if (checkIsNull($("#patientNameId").val()) || checkIsNull($("#dobId").val()) || checkIsNull($("#userEmailId").val())
			|| checkIsNull($("#passwordId").val()) || checkIsNull($("#contactId").val()) || checkIsNull($("input[name='genderRadio']:checked").val())) {
			alert("Please fill all the required data");
		} else {
			let requestBody = {
				"patientName": $("#patientNameId").val(),
				"dob": $("#dobId").val(),
				"emailId": $("#userEmailId").val(),
				"password": $("#passwordId").val(),
				"contactNum": $("#contactId").val(),
				"gender": $("input[name='genderRadio']:checked").val()
			}
			$.ajax({
				type: 'post',
				contentType: "application/json",
       			dataType: 'json',
        		cache: false,
        		cache: false,
				url: "http://localhost:8090/register",
				data: JSON.stringify(requestBody),
				success: function(lresponse) {
					$('#regModelId').modal('hide');
					alert("Registerd sucessfully!!!");
				}, error: function(error) {
					alert("Something went wrong");
				}
			});
		}
	}
	function resetData() {
		$("#patientNameId").val("");
		$("#dobId").val("");
		$("#userEmailId").val("");
		$("#passwordId").val("");
		$("#contactId").val("");
		$("input[type=radio][name=genderRadio]").prop("checked", false);

	}
	$(document).ready(function() {
		$('#regModelId').on('hidden.bs.modal', function(e) {
			resetData();
		})
	});
