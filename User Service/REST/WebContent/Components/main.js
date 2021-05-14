$(document).on("click", "#btnSave", function(event)
{
//Form validation-------------------
var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}	
	
// Clear alerts--------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

 // If valid------------------------
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "UserServlet",
type : type,
data : $("#formUser").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});

////////////////////////////////////////////////////////////////////////////////

function onItemSaveComplete(response, status)
{ 
    if (status == "success") 
    { 
        var resultSet = JSON.parse(response); 
        if (resultSet.status.trim() == "success") 
        { 
            $("#alertSuccess").text("Successfully saved."); 
            $("#alertSuccess").show(); 
            $("#divUsersGrid").html(resultSet.data); 
        } 
        else if (resultSet.status.trim() == "error") 
        { 
            $("#alertError").text(resultSet.data); 
            $("#alertError").show(); 
        } 
    } 
    else if (status == "error") 
    { 
        $("#alertError").text("Error while saving."); 
        $("#alertError").show(); 
    } else
    { 
        $("#alertError").text("Unknown error while saving.."); 
        $("#alertError").show(); 
    }
    $("#hidUserIDSave").val(""); 
    $("#formUser")[0].reset(); 
}

 

$(document).on("click", ".btnUpdate", function(event)
{ 
    $("#hidUserIDSave").val($(this).data("userid"));
    $("#user_level").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#email").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#fname").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#lname").val($(this).closest("tr").find('td:eq(3)').text());
    $("#dob").val($(this).closest("tr").find('td:eq(4)').text());
    $("#address").val($(this).closest("tr").find('td:eq(5)').text());
    $("#tp_number").val($(this).closest("tr").find('td:eq(6)').text());
    
});

 

$(document).on("click", ".btnRemove", function(event)
{ 
    $.ajax( 
    { 
        url : "UserServlet", 
        type : "DELETE", 
        data : "user_id=" + $(this).data("userid"),
        dataType : "text", 
        complete : function(response, status) 
        { 
            onItemDeleteComplete(response.responseText, status); 
        } 
    }); 
})

 

function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
{ 
    var resultSet = JSON.parse(response); 
    if (resultSet.status.trim() == "success") 
    { 
        $("#alertSuccess").text("Successfully deleted."); 
        $("#alertSuccess").show(); 
        $("#divUsersGrid").html(resultSet.data); 
    } 
    else if (resultSet.status.trim() == "error") 
    { 
        $("#alertError").text(resultSet.data); 
        $("#alertError").show(); 
    } 
     } 
    else if (status == "error") 
    { 
        $("#alertError").text("Error while deleting."); 
        $("#alertError").show(); 
    } 
    else
    { 
        $("#alertError").text("Unknown error while deleting.."); 
        $("#alertError").show(); 
    }
} 



function validateItemForm()
{
	console.log("Error");
	//User Level
	if ($("#user_level").val().trim() == "")
	{
		return "Enter User Level.";
	}
	//Email
	if ($("#email").val().trim() == "")
	{
		return "Enter Email.";
	}
	//First Name
	if ($("#fname").val().trim() == "")
	{
		return "Enter First Name.";
	}
	//Last Name
	if ($("#lname").val().trim() == "")
	{
		return "Enter Last Name.";
	}
	//Date of Birth
	if ($("#dob").val().trim() == "")
	{
		return "Enter Date Of Birth.";
	}
	//Address
	if ($("#address").val().trim() == "")
	{
	return "Enter Address.";
	}
	//Telephone number
	if ($("#tp_number").val().trim() == "")
	{
	return "Enter Telephone Number.";
	}
	return true;
}