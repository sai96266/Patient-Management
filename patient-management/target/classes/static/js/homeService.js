var doctorNameData = [] ,appointmentTableData = [];
function checkIsNull(value){
    return value === "" || value === undefined || value === null ? true : false;
}
function logout(){
    window.location = "login";
}
function onPageload(){
    getDoctorNameList();
    getAppointmentTableData();
    $("#checkupHistoryMainDivId").hide();
    $("#billingMainDivId").hide();
}
function getDoctorNameList(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8090/doctorDetails",
        success:function(response)
        {
            doctorNameData = response;
            $("#doctorNameId").empty();
            $("#doctorNameId").append('<option value="">Select Name</option>');
            doctorNameData.forEach(element => {
                $("#doctorNameId").append("<option value='"+JSON.stringify(element)+"'>"+element.name+"</option>");
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function getAppointmentTableData(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8090/fetchAppointmentData",
        success:function(response)
        {
            appointmentTableData = response;
           
            $("#appointmentTableBodyId").empty();
            appointmentTableData.forEach((element,index) => {
                let srlno = index+1;
                let checkupAction = element.appointmentStatus == "pending" ? '<button type="button" tableData="'+element.appointmentId+'" tableAmt="'+element.charges+'" class="btn btn-info btn-sm checkupBtnCls" data-toggle="modal" data-target="#checkUpModalId">Check Up</button>' :element.appointmentStatus;
                $("#appointmentTableBodyId").append(
                    '<tr>'+
                    '<th scope="row">'+srlno+'</th>'+
                    '<td>'+element.appointmentId+'</td>'+
                    '<td>'+element.name+'</td>'+
                    '<td>'+element.charges+'</td>'+
                    '<td>'+element.appointmentDate+'</td>'+
                    '<td>'+element.appointmentTime+'</td>'+
                    '<td>'+checkupAction+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function submitCheckupData(){
    if(checkIsNull($("#appointmentId").val()) || checkIsNull($("#bloodPresureId").val()) || checkIsNull($("#weightId").val()) 
        || checkIsNull($("#diseaseId").val()) || checkIsNull($("#remarkId").val())){
            alert("Please fill all the required data");
    }else{
        let requestBody = {
            "appointmentId" : $("#appointmentId").val(),
            "amount"		: $('#hiddenAmountId').val(),
            "bloodPressure" : $("#bloodPresureId").val(),
            "weight"        : $("#weightId").val(),
            "disease"   	: $("#diseaseId").val(),
            "remark"        : $("#remarkId").val()
        }
        $.ajax({
            type:'post',
            contentType: "application/json",
       		dataType: 'json',
        	cache: false,
            url:"http://localhost:8090/saveCheckupData",
            data:JSON.stringify(requestBody),
            success:function(lresponse)
            {
                $('#checkUpModalId').modal('hide');
                getAppointmentTableData()
                alert("Data submitted sucessfully!!!");
            },error:function(error)
            {
                alert("Something went wrong");
            }
        });
    }
}
function submitAppointmentData(){
    if(checkIsNull($("#doctorNameId").val()) || checkIsNull($("#chargesFeeId").val()) || checkIsNull($("#appointmentDate").val()) 
        || checkIsNull($("#appontmentTimeId").val())){
            alert("Please fill all the required data");
    }else{
        let requestBody = {
			"patientId" : localStorage.getItem("patientId"),
            "doctorId"  : JSON.parse($("#doctorNameId").val()).doctorId,
            "charges"   : $("#chargesFeeId").val(),
            "appointmentDate" : $("#appointmentDate").val(),
            "appointmentTime"   : $("#appontmentTimeId").val()
        }
        $.ajax({
            type:'post',
            contentType: "application/json",
       		dataType: 'json',
        	cache: false,
            url:"http://localhost:8090/submitAppointmentData",
            data:JSON.stringify(requestBody),
            success:function(lresponse)
            {
                getAppointmentTableData();
                alert("Data submitted sucessfully!!!");
            },error:function(error)
            {
                alert("Something went wrong");
            }
        });
    }
}
function resetCheckUpData(){
    $("#bloodPresureId").val(""),
    $("#weightId").val(""),
    $("#diseaseId").val(""),
    $("#remarkId").val("")
}
function getCheckupHistorydata(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8090/fetchCheckupData",
        success:function(response)
        {
            let checkupHistoryData =  response;
           
            $("#checkupHistoryTableBodyId").empty();
            checkupHistoryData.forEach((element,index) => {
                let srlno = index+1;
                $("#checkupHistoryTableBodyId").append(
                    '<tr>'+
                    '<th scope="row">'+srlno+'</th>'+
                    '<td>'+element.appointmentId+'</td>'+
                    '<td>'+element.disease+'</td>'+
                    '<td>'+element.bloodPressure+'</td>'+
                    '<td>'+element.weight+'</td>'+
                    '<td>'+element.remark+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function getBillingData(){
    $.ajax({
        type:'get',
        contentType: "application/json",
       	dataType: 'json',
        cache: false,
        url:"http://localhost:8090/fetchBillingData",
        success:function(response)
        {
            let billingdata = response;
              
            $("#billingTableBodyId").empty();
            billingdata.forEach((element,index) => {
                let srlno = index+1;
                let checkupAction = element.paymentMode == "pending" ? '<button type="button" charges="'+element.amount+'" appointmentId="'+element.appointmentId+'" billingId="'+element.billingId+'" class="btn btn-info btn-sm billingBtnCls" data-toggle="modal" data-target="#processToPayModalId">Process to pay</button>' :element.paymentMode;
                $("#billingTableBodyId").append(
                    '<tr>'+
                    '<th>'+srlno+'</th>'+
                    '<td>'+element.appointmentId+'</td>'+
                    '<td>'+element.amount+'</td>'+
                    '<td>'+checkupAction+'</td>'+
                  '</tr>'
                )
            });
        },error:function(error)
        {
            alert("Something went wrong");
        }
    });
}
function submitPaymentData(){
    if(checkIsNull($("#paymentModeId").val())){
            alert("Please fill all the required data");
    }else{
        let requestBody = {
        	"billingId":$("#hiddenBillingId").val(),
            "appointmentId":$("#hiddenAppointmentId").val(),
            "paymentMode" : $("#paymentModeId").val(),
            "amount"  : $("#ammountId").val()
        }
        $.ajax({
            type:'post',
            contentType: "application/json",
       		dataType: 'json',
        	cache: false,
            url:"http://localhost:8090/processToBilling",
            data:JSON.stringify(requestBody),
            success:function(response)
            {
                $('#processToPayModalId').modal('hide');
                getBillingData()
                alert("Data submitted sucessfully!!!");
            },error:function(error)
            {
                alert("Something went wrong");
            }
        });
    }
}
$(document).ready(function () {
    $(".menuCls").on("click", function(){
        $(".menuCls").removeClass("active");
        $(this).addClass("active");
        let type = $(this).attr("menuType");
        $("#appointmentMainDiv").hide();
        $("#checkupHistoryMainDivId").hide();
        $("#billingMainDivId").hide();
        if(type == "APPOINTMENT"){
            $("#appointmentMainDiv").show();
            getAppointmentTableData();
        }else if(type == "CHECKUP"){
            $("#checkupHistoryMainDivId").show();
            getCheckupHistorydata();
        }else if(type == "BILLING"){
            $("#billingMainDivId").show();
            getBillingData();
        }
    })
    
    $("#doctorNameId").on("change",function(){
        if(!checkIsNull($(this).val())){
            let data = JSON.parse($(this).val());
            $("#chargesFeeId").val(data.charges);
        }else{
            $("#chargesFeeId").val("");
        }
    })
    $(document).on("click",".checkupBtnCls",function(){
        $('#appointmentId').val($(this).attr("tableData"));
         $('#hiddenAmountId').val($(this).attr("tableAmt"));
    })
    $(document).on("click",".billingBtnCls",function(){
        $("#ammountId").val($(this).attr("charges"));
        $("#hiddenAppointmentId").val($(this).attr("appointmentId"));
        $("#hiddenBillingId").val($(this).attr("billingId"));
    })
    $('#checkUpModalId').on('hidden.bs.modal', function (e) {
        resetCheckUpData();
    })
    $('#processToPayModalId').on('hidden.bs.modal', function (e) {
        $("#paymentModeId").val("");
        $("#ammountId").val("");
    })
});