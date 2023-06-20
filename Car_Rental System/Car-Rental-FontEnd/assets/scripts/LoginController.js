let regLoginUsername = /^[A-z0-9]{6,10}$/;
let regLoginPassword = /^[A-z0-9@#$%&!*]{8,}$/;
let regName = /^[A-z .]{3,}$/;
let regAddress = /^[A-z ,.0-9]{3,}$/;
let regContactNo = /^(0)[1-9][0-9][0-9]{7}$/;
let regEmail = /^[a-z0-9]{3,}(@)[a-z]{3,}(.)[a-z]{2,3}$/;
let regDrivingLicenceNo = /^(B)[0-9]{7}$/;
let regNicNo = /^[0-9]{9}(V)|[0-9]{12}$/;

let baseUrl = "http://localhost:8080/Car_Rental_BackEnd_war/";

$('#txtUserName,#txtPassword,#inputName,#inputContactNo,#inputAddress,#inputEmail,#inputDrivingLicence,#inputNIC,#inputUserName,#inputPassword,#inputfile1,#inputfile2,#inputfile3').on('keydown', function (event) {
    if (event.key == "Tab") {
        event.preventDefault();
    }
});
$('#txtUserName,#txtPassword').on('blur', function () {
    addLoginFormValidation();
});

function addLoginFormValidation() {
    var username = $("#txtUserName").val();
    if (regLoginUsername.test(username)) {
        $("#txtUserName").css('border', '2px solid green');
        $("#userNameError").text("");
        var password = $('#txtPassword').val();
        if (regLoginPassword.test(password)) {
            $("#txtPassword").css('border', '2px solid green');
            $("#passwordError").text("");
            return true;
        } else {
            $("#txtPassword").css('border', '2px solid red');
            $("#passwordError").text("Password is not valid. Enter valid password.");
            return false;
        }
    } else {
        $("#txtUserName").css('border', '2px solid red');
        $("#userNameError").text("Username is not valid. Enter valid username");
        return false;
    }
}

$('#txtUserName').on('keyup', function (event) {
    setLoginButtonDisableOrNot();
    if (event.key === "Enter") {
        checkIfLoginUserFormValid();
    }
});
$('#txtPassword').on('keyup', function (event) {
    setLoginButtonDisableOrNot();
    if (event.key === "Enter") {
        checkIfLoginUserFormValid();
    }
});
$('#cmbUserType').change(function () {
    var userType = $('#cmbUserType').find('option:selected').text();
    var username = $('#txtUserName').val();
    var password = $('#txtPassword').val();
    console.log(userType);
    if (userType != "-Select User Type-" && username != "" && password != "") {
        $("#btnLogin").prop('disabled', false);
    } else {
        $("#btnLogin").prop('disabled', true);
    }
});

function setLoginButtonDisableOrNot() {
    let check = addLoginFormValidation();
    var type = $("#cmbUserType").find('option:selected').text();
    if (check && type != "-Select User Type-") {
        $("#btnLogin").prop('disabled', false);
    } else {
        $("#btnLogin").prop('disabled', true);
    }
}

function checkIfLoginUserFormValid() {
    var username = $('#txtUserName').val();
    if (regLoginUsername.test(username)) {
        $('#txtPassword').focus();
        var password = $('#txtPassword').val();
        var response = regLoginPassword.test(password);
        var type = $('#cmbUserType').find('option:selected').text();
        if (response && type != "-Select User Type-") {
            loginUser();
        } else {
            $('#txtPassword').focus();
        }
    } else {
        $('#txtUserName').focus();
    }
}

$('#inputUserType').change(function () {
    var userType = $('#inputUserType').find('option:selected').text();
    if (userType === "Admin") {
        $("#inputName").prop('disabled', false);
        $("#inputAddress").prop('disabled', false);
        $("#inputContactNo").prop('disabled', false);
        $("#inputEmail").prop('disabled', false);
        $("#inputUserName").prop('disabled', false);
        $("#inputPassword").prop('disabled', false);
        $("#inputDrivingLicence").prop('disabled', true);
        $("#inputNIC").prop('disabled', true);
        $("#inputfile1").prop('disabled', true);
        $("#inputfile2").prop('disabled', true);
        $("#inputfile3").prop('disabled', true);
        generateAdminId();
    } else if (userType === "Customer") {
        $("#inputName").prop('disabled', false);
        $("#inputAddress").prop('disabled', false);
        $("#inputContactNo").prop('disabled', false);
        $("#inputEmail").prop('disabled', false);
        $("#inputUserName").prop('disabled', false);
        $("#inputPassword").prop('disabled', false);
        $("#inputDrivingLicence").prop('disabled', false);
        $("#inputNIC").prop('disabled', false);
        $("#inputfile1").prop('disabled', false);
        $("#inputfile2").prop('disabled', false);
        $("#inputfile3").prop('disabled', false);
        generateCustomerId();
    } else {
        disableAllComponents();
    }
});

function disableAllComponents() {
    $("#inputName").prop('disabled', true);
    $("#inputAddress").prop('disabled', true);
    $("#inputContactNo").prop('disabled', true);
    $("#inputEmail").prop('disabled', true);
    $("#inputUserName").prop('disabled', true);
    $("#inputPassword").prop('disabled', true);
    $("#inputDrivingLicence").prop('disabled', true);
    $("#inputNIC").prop('disabled', true);
    $("#inputfile1").prop('disabled', true);
    $("#inputfile2").prop('disabled', true);
    $("#inputfile3").prop('disabled', true);
    $('#txtId').val("");
}

function generateAdminId() {
    $.ajax({
        url: baseUrl + "api/v1/admin/generateAdminID",
        method: "GET",
        success: function (res) {
            $('#txtId').val(res.data);
        }
    });
}

function generateCustomerId() {
    $.ajax({
        url: baseUrl + "api/v1/customer/generateCustomerId",
        method: "GET",
        success: function (res) {
            $('#txtId').val(res.data);
        }
    })
}

$('#inputName,#inputAddress,#inputContactNo,#inputNIC,#inputDrivingLicence,#inputEmail,#inputUserName,#inputPassword').on('keyup', function (event) {
    if (event.key == "Enter") {
        checkIfSignUpUserFormValid();
    }
});

function checkIfSignUpUserFormValid() {
    var name = $('#inputName').val();
    if (regName.test(name)) {
        $('#inputContactNo').focus();
        var contactNo = $('#inputContactNo').val();
        if (regContactNo.test(contactNo)) {
            $('#inputAddress').focus();
            var address = $('#inputAddress').val();
            if (regAddress.test(address)) {
                $('#inputEmail').focus();
                var email = $('#inputEmail').val();
                if (regEmail.test(email)) {
                    let usertype = $("#inputUserType").find('option:selected').text();
                    if (usertype === "Customer") {
                        $('#inputDrivingLicence').focus();
                        var drivingLicence = $('#inputDrivingLicence').val();
                        if (regDrivingLicenceNo.test(drivingLicence)) {
                            $('#inputNIC').focus();
                            var nicNo = $('#inputNIC').val();
                            if (regNicNo.test(nicNo)) {
                                $('#inputUserName').focus();
                                var username = $('#inputUserName').val();
                                if (regLoginUsername.test(username)) {
                                    $('#inputPassword').focus();
                                    var password = $('#inputPassword').val();
                                    if (regLoginPassword.test(password)) {
                                        if ($('#inputfile1').val() != "" && $('#inputfile2').val() != "" && $('#inputfile3').val() != "") {
                                            let res = confirm("Do you want to add this customer?");
                                            if (res) {
                                                addCustomer();
                                            }
                                        } else {
                                            alert("Please fill all fields of customer...")
                                        }
                                    } else {
                                        $('#inputPassword').focus();
                                    }
                                } else {
                                    $('#inputUserName').focus();
                                }
                            } else {
                                $('#inputNIC').focus();
                            }
                        } else {
                            $('#inputDrivingLicence').focus();
                        }
                    } else if (usertype === "Admin") {
                        $('#inputUserName').focus();
                        var username = $('#inputUserName').val();
                        if (regLoginUsername.test(username)) {
                            $('#inputPassword').focus();
                            var password = $('#inputPassword').val();
                            if (regLoginPassword.test(password)) {
                                let res = confirm("Do you want to add this admin?");
                                if (res) {
                                    addAdmin();
                                }
                            } else {
                                $('#inputPassword').focus();
                            }
                        } else {
                            $('#inputUserName').focus();
                        }

                    }
                } else {
                    $('#inputEmail').focus();
                }
            } else {
                $('#inputAddress').focus();
            }
        } else {
            $('#inputContactNo').focus();
        }
    } else {
        $('#inputName').focus();
    }
}

$('#inputName').on('keyup', function () {
    checkInputName();
})

function checkInputName() {
    var name = $('#inputName').val();
    if (regName.test(name)) {
        $("#inputName").css('border', '2px solid green');
        return true;
    } else {
        $("#inputName").css('border', '2px solid red');
        return false;
    }
}

$('#inputContactNo').on('keyup', function () {
    checkInputContactNo();
})

function checkInputContactNo() {
    var contactNo = $('#inputContactNo').val();
    if (regContactNo.test(contactNo)) {
        $("#inputContactNo").css('border', '2px solid green');
        return true;
    } else {
        $("#inputContactNo").css('border', '2px solid red');
        return false;
    }
}

$('#inputAddress').on('keyup', function () {
    checkInputAddress();
})

function checkInputAddress() {
    var address = $('#inputAddress').val();
    if (regAddress.test(address)) {
        $("#inputAddress").css('border', '2px solid green');
        return true;
    } else {
        $("#inputAddress").css('border', '2px solid red');
        return false;
    }
}

$('#inputEmail').on('keyup', function () {
    checkInputEmail();
})

function checkInputEmail() {
    var email = $('#inputEmail').val();
    if (regEmail.test(email)) {
        $("#inputEmail").css('border', '2px solid green');
        return true;
    } else {
        $("#inputEmail").css('border', '2px solid red');
        return false;
    }
}

$('#inputDrivingLicence').on('keyup', function () {
    checkInputDrivingLicence();
})

function checkInputDrivingLicence() {
    var drivingLicence = $('#inputDrivingLicence').val();
    if (regDrivingLicenceNo.test(drivingLicence)) {
        $("#inputDrivingLicence").css('border', '2px solid green');
        return true;
    } else {
        $("#inputDrivingLicence").css('border', '2px solid red');
        return false;
    }
}

$('#inputNIC').on('keyup', function () {
    checkInputNIC();
})

function checkInputNIC() {
    var nicNo = $('#inputNIC').val();
    if (regNicNo.test(nicNo)) {
        $("#inputNIC").css('border', '2px solid green');
        return true;
    } else {
        $("#inputNIC").css('border', '2px solid red');
        return false;
    }
}

$('#inputUserName').on('keyup', function () {
    checkInputUserName();
})

function checkInputUserName() {
    var userName = $('#inputUserName').val();
    if (regLoginUsername.test(userName)) {
        $("#inputUserName").css('border', '2px solid green');
        return true;
    } else {
        $("#inputUserName").css('border', '2px solid red');
        return false;
    }
}

$('#inputPassword').on('keyup', function () {
    checkInputPassword();
})

function checkInputPassword() {
    var password = $('#inputPassword').val();
    if (regLoginPassword.test(password)) {
        $("#inputPassword").css('border', '2px solid green');
        return true;
    } else {
        $("#inputPassword").css('border', '2px solid red');
        return false;
    }
}


function addCustomer() {

    let id = $('#txtId').val();
    let name = $('#inputName').val();
    let address = $('#inputAddress').val();
    let contactNo = $('#inputContactNo').val();
    let email = $('#inputEmail').val();
    let nicNo = $('#inputNIC').val();
    let licenceNo = $('#inputDrivingLicence').val();
    let username = $('#inputUserName').val();
    let password = $('#inputPassword').val();
    let status = "Pending";

    var customer = {
        customerId: id,
        name: name,
        address: address,
        contactNo: contactNo,
        email: email,
        nicNo: nicNo,
        licenceNo: licenceNo,
        username: username,
        password: password,
        status: status
    }

    $.ajax({
        url: baseUrl + "api/v1/customer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function (resp) {
            uploadCustomerImages(id);
            swal({
                title: "Confirmation",
                text: "Customer Added Successfully",
                icon: "success",
                button: "Close",
                timer: 2000
            });
        },
        error: function (ob) {
            swal({
                title: "Error!",
                text: "Customer Not Added Successfully",
                icon: "error",
                button: "Close",
                timer: 2000
            });
        }
    })
}

function uploadCustomerImages(id) {
    var fileObjectNic1 = $('#inputfile1')[0].files[0];
    var fileNameNic1 = id + "-nicfront-" + $('#inputfile1')[0].files[0].name;

    var fileObjectNic2 = $('#inputfile2')[0].files[0];
    var fileNameNic2 = id + "-nicback-" + $('#inputfile2')[0].files[0].name;

    var fileObjectLicence = $('#inputfile3')[0].files[0];
    var fileNameLicence = id + "-licence-" + $('#inputfile3')[0].files[0].name;

    var data = new FormData();
    data.append("nicf", fileObjectNic1, fileNameNic1);
    data.append("nicb", fileObjectNic2, fileNameNic2);
    data.append("licenceImg", fileObjectLicence, fileNameLicence);

    $.ajax({
        url: baseUrl + "api/v1/customer/up/" + id,
        method: "PUT",
        async: true,
        contentType: false,
        processData: false,
        data: data,
        success: function (res) {
            console.log("Uploaded");
            clearSignupTextFields();
        }
    })
}

function clearSignupTextFields() {
    $('#txtId').val("");
    $('#inputUserType').val("-Select User Type-");
    $('#inputName').val("");
    $('#inputContactNo').val("");
    $('#inputAddress').val("");
    $('#inputEmail').val("");
    $('#inputDrivingLicence').val("");
    $('#inputNIC').val("");
    $('#inputUserName').val("");
    $('#inputPassword').val("");
    $('#inputfile1').val("");
    $('#inputfile2').val("");
    $('#inputfile3').val("");
    $('#inputName').css('border', '1px solid #ced4da');
    $('#inputContactNo').css('border', '1px solid #ced4da');
    $('#inputAddress').css('border', '1px solid #ced4da');
    $('#inputEmail').css('border', '1px solid #ced4da');
    $('#inputDrivingLicence').css('border', '1px solid #ced4da');
    $('#inputNIC').css('border', '1px solid #ced4da');
    $('#inputUserName').css('border', '1px solid #ced4da');
    $('#inputPassword').css('border', '1px solid #ced4da');
    disableAllComponents();
}

function addAdmin() {
    let id = $('#txtId').val();
    let name = $('#inputName').val();
    let address = $('#inputAddress').val();
    let contactNo = $('#inputContactNo').val();
    let email = $('#inputEmail').val();
    let username = $('#inputUserName').val();
    let password = $('#inputPassword').val();

    var admin = {
        adminId: id,
        name: name,
        address: address,
        contact: contactNo,
        email: email,
        username: username,
        password: password
    }

    $.ajax({
        url: baseUrl + "api/v1/admin",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function (resp) {
            clearSignupTextFields();
            swal({
                title: "Confirmation",
                text: "Admin Added Successfully",
                icon: "success",
                button: "Close",
                timer: 2000
            });
        },
        error: function (ob) {
            swal({
                title: "Error!",
                text: "Admin Not Added Successfully",
                icon: "error",
                button: "Close",
                timer: 2000
            });
        }
    })
}

$('#btnCreate').click(function () {
    if ($('#inputUserType').val() === "Customer") {
        if ($('#inputName').val() != "") {
            if ($('#inputContactNo').val() != "") {
                if ($('#inputAddress').val() != "") {
                    if ($('#inputEmail').val() != "") {
                        if ($('#inputDrivingLicence').val() != "") {
                            if ($('#inputNIC').val() != "") {
                                if ($('#inputUserName').val() != "") {
                                    if ($('#inputPassword').val() != "") {
                                        if ($('#inputfile1').val() != "") {
                                            if ($('#inputfile2').val() != "") {
                                                if ($('#inputfile3').val() != "") {
                                                    let res = confirm("Do you want to add this customer?");
                                                    if (res) {
                                                        addCustomer();
                                                    }
                                                } else {
                                                    alert("Please upload image of licence");
                                                    $('#inputfile3').focus();
                                                }
                                            } else {
                                                alert("Please upload back image of NIC");
                                                $('#inputfile2').focus();
                                            }
                                        } else {
                                            alert("Please upload front image of NIC");
                                            $('#inputfile1').focus();
                                        }
                                    } else {
                                        alert("Please enter password...");
                                        $('#inputPassword').focus();
                                    }
                                } else {
                                    alert("Please enter username...");
                                    $('#inputUserName').focus();
                                }
                            } else {
                                alert("Please enter your NIC No...");
                                $('#inputNIC').focus();
                            }
                        } else {
                            alert("Please enter your licence No...");
                            $('#inputDrivingLicence').focus();
                        }
                    } else {
                        alert("Please enter your email...");
                        $('#inputEmail').focus();
                    }
                } else {
                    alert("Please enter your address...");
                    $('#inputAddress').focus();
                }
            } else {
                alert("Please enter your Contact No...");
                $('#inputContactNo').focus();
            }
        } else {
            alert("Please enter your name...");
            $('#inputName').focus();
        }
    } else if ($('#inputUserType').val() === "Admin") {
        if ($('#inputName').val() != "") {
            if ($('#inputContactNo').val() != "") {
                if ($('#inputAddress').val() != "") {
                    if ($('#inputEmail').val() != "") {
                        if ($('#inputUserName').val() != "") {
                            if ($('#inputPassword').val() != "") {
                                let res = confirm("Do you want to add this admin?");
                                if (res) {
                                    addAdmin();
                                }
                            } else {
                                alert("Please enter password...");
                                $('#inputPassword').focus();
                            }
                        } else {
                            alert("Please enter username...");
                            $('#inputUserName').focus();
                        }
                    } else {
                        alert("Please enter your email...");
                        $('#inputEmail').focus();
                    }
                } else {
                    alert("Please enter your address...");
                    $('#inputAddress').focus();
                }
            } else {
                alert("Please enter your Contact No...");
                $('#inputContactNo').focus();
            }
        } else {
            alert("Please enter your name...");
            $('#inputName').focus();
        }
    } else {
        alert("Please select user type")
    }
});

$('#btnClear').click(function () {
    clearSignupTextFields();
});

$('#btnLogin').click(function () {
    var userType = $('#cmbUserType').find('option:selected').text();

    if ($('#txtUserName').val() != "" && $('#txtPassword').val() != "" && userType != "-Select User Type-") {
        loginUser();
    }


});

function loginUser() {
    var username = $('#txtUserName').val();
    var password = $('#txtPassword').val();
    var userType = $('#cmbUserType').find('option:selected').text();

    console.log(userType);

    if (userType === "Admin") {
        searchAdmin(userType, username, password);
    } else if (userType === "Customer") {
        searchCustomer(userType, username, password);
    } else if (userType === "Driver") {
        searchDriver(userType, username, password);
    }
}

function loginSave(userType, username, password) {
    let logId = $('#txtLogId').val();
    console.log(logId);
    $.ajax({
        url: baseUrl + "api/v1/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(
            {
                loginId: logId,
                username: username,
                password: password,
                role: userType
            }
        ),
        success: function (res) {
            if (userType==="Admin"){
                location.replace("AdminDashboard.html");
            } else if (userType==="Customer"){
                location.replace("CustomerDashboard.html");
            } else if (userType==="Driver"){
                location.replace("DriverDashboard.html");
            }
            console.log("Login data saved");
        }
    })
}

$(function () {
    getNewLoginId();
});

function getNewLoginId() {
    $.ajax({
        url: baseUrl + "api/v1/login/generateLogId",
        method: "GET",
        success: function (res) {
            $('#txtLogId').val(res.data);
        }
    });
}

function searchAdmin(userType, username, password) {
    $.ajax({
        url: baseUrl + "api/v1/admin/" + username + "/" + password,
        method: "GET",
        success: function (res) {
            if (res.data === true) {
                loginSave(userType, username, password);

            } else {
                alert(res.massage);
            }
        }
    });
}

function searchCustomer(userType, username, password) {
    $.ajax({
        url: baseUrl + "api/v1/customer/" + username + "/" + password,
        method: "GET",
        success: function (res) {
            console.log(res.data);
            if (res.data === true) {
                loginSave(userType, username, password);
            } else {
                alert(res.massage);
            }
        }
    })
}

function searchDriver(userType, username, password) {
    $.ajax({
        url: baseUrl + "api/v1/driver/" + username + "/" + password,
        method: "GET",
        success: function (res) {
            console.log(res.data);
            if (res.data === true) {
                loginSave(userType, username, password);
            } else {
                alert(res.massage);
            }
        }
    })
}

