"use strict";
$(document).ready(function () {
	/* Get All Control */
    var keyword = $('#txt-search'),
        className = $('#class-name'),
        studentInfo = $('div#student-info'),
        txtId = $('#txt-id'),
        txtName = $('#txt-name'),
        cboGender = $('#cbo-gender'),
        cboUniversity = $('#cbo-university'),
        cboClass = $('#cbo-class'),
        btnAdd = $('#add-new-student'),
        alertId = $('#alert-id'),
        validateId = $('#validate-id'),
        alertName = $('#alert-name'),
        validateName = $('#validate-name');
    
    /* Load data into Table */
    list();
    
    function list() {
        $.ajax({
            url: 'liststudent.etv',
            method: 'POST',
            data: {
                stuName: keyword.val(),
                className: className.val(),
            },
            success: function (data) {
                studentInfo.html(listDetail(data));
                
                /* Button for show Update Modal */
                $('img.edit-student').click(function(){
                    txtId.prop('readonly',true);
                    var td = $(this).parents('tr').find('td');
                    var gender = td.eq(2).text();
                    gender = (gender == "Male") ? 1 : 0;
                    txtId.val(td.eq(0).text());
                    txtName.val(td.eq(1).text());
                    cboGender.val(gender);
                    cboUniversity.val(td.eq(3).text());
                    cboClass.val(td.eq(4).text());
                    btnAdd.text("Update");
                    btnAdd.unbind('click');
                    btnAdd.click(function(){
                        updateStudent(td);
                    });
                });
                
                /* Delete Button Click Event */
                $('img.delete-student').click(function(){
                    var id = $(this).parents('tr').find('td').eq(0).text();
                    swal({   
                        title: "Are you sure?",   
                        text: "This record will be deleted in Database !",   
                        type: "warning",   
                        showCancelButton: true,   
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Yes, delete it!",   
                        closeOnConfirm: false },
                        function(){
                            deleteStudent(id);
                            swal({
                                title: 'Deleted Sucessfully!',
                                type: "success"
                            });
                        }
                    );
                });
            }
        });
    }
    function listDetail(data) {
        var str = "";
        str += '<table class="table table-hover">' +
                    '<tr>' +
                        '<th>ID</th>' +
                        '<th>Name</th>' +
                        '<th>Gender</th>' +
                        '<th>University</th>' +
                        '<th>Class</th>' +
                        '<th>Actions</th>' +
                    '</tr>';
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                str += '<tr>' +
                            '<td>' + data[i].id + '</td>' +
                            '<td>' + data[i].name + '</td>' +
                            '<td>' + data[i].gender + '</td>' +
                            '<td>' + data[i].university + '</td>' +
                            '<td>' + data[i].className + '</td>' +
                            '<td>' +
                                '<img src="images/edit.png" class="edit-student" data-toggle="modal" data-target="#bootstrapModal">' +
                                '<img src="images/delete.png" class="delete-student">' +
                            '</td>' +
                       '</tr>';
            }
        }
        else{
            str += '<tr>' +
                        '<td class="text-center" colspan="6">' +
                            '[No Record]' +
                        '</td>' +
                   '</tr>';
        }
        str += '</table>';
        return str;
    }
    
    /* Retrieve data on typing on Search Textbox */
    keyword.keyup(function(){
        list();
    });
    
    /* Filter Data in Table by selecting on Combox Box */
    className.change(function(){
        list();
    });    
    
    /* Add Button for show modal Info */
    $('#add-modal').click(function(){
        btnAdd.text("Add");
        $("#add-new-student").unbind('click');
        /* Add button in Modal */
        btnAdd.click(function(){
            if(txtId.val() == ""){
                validateId.addClass('has-error');
                alertId.text('* Can not empty!').show();
            }
            
            if (txtName.val() == ""){
                alertName.show();
                validateName.addClass('has-error');
            }
            
            if(!isNumber(txtId.val())){
                alertId.text('* Only number is allowed!').show();
            }

            if (validateId.hasClass('has-error') || txtId.val() == "" || txtName.val() == "" || !isNumber(txtId.val())){
            	return;
            }else
                addNewStudent();
        });
    });
    
    /* Add New Student to DB */
    function addNewStudent(){
        $.ajax({
            url:'addstudent.etv',
            method:'POST',
            data:{
                id: txtId.val(),
                name: txtName.val(),
                gender: cboGender.val(),
                university: cboUniversity.val(),
                className: cboClass.val()
            },
            success:function(data){
                list();
                $('#bootstrapModal').modal('hide');
                swal({
                    title: 'Insert Sucessfully!',
                    type: "success"
                });
            }
        });
    }
    
    /* Delete Student */
    function deleteStudent(id){
        $.ajax({
            url:'deletestudent.etv' ,
            method:'POST',
            data:{
                id:id,
            },
            success:function(data){
                if (data == "success") {
                    list();
                }
            }
        });
    }
    
    /* Update Student Info */
    function updateStudent(td){
        $.ajax({
            url: 'updatestudent.etv',
            method: 'POST',
            data: {
                id: txtId.val(),
                name: txtName.val(),
                gender: cboGender.val(),
                university: cboUniversity.val(),
                className: cboClass.val()
            },
            success: function (data) {
                if (data == "success") {
                	// Because if cbo.text = sum of option's text
                	var gender = (cboGender.val() == 1)?"Male":"Female";
                    td.eq(0).text(txtId.val());
                    td.eq(1).text(txtName.val());
                    td.eq(2).text(gender);
                    td.eq(3).text(cboUniversity.val());
                    td.eq(4).text(cboClass.val());
                    $('#bootstrapModal').modal('hide');
                    swal({
                        title: 'Update Sucessfully!',
                        type: "success"
                    });
                }
            }
        });
    }
    
    /* Validate ID */
    function validateDuplicateId(id){
        $.ajax({
            url:'validation.etv',
            data:{
                id:id
            },
            success:function(data){
                if(data=="found"){
                    validateId.addClass('has-error');
                    alertId.text('* Duplicate ID').show();
                }else{
                    validateId.removeClass('has-error');
                    alertId.hide();
                }
            }
        });
    }
    
    txtId.blur(function(){
        var id = "131N" + $(this).val();
        validateDuplicateId(id);
    });
    
    txtId.focus(function(){
        validateId.removeClass('has-error');
        alertId.hide();
    });
    
    txtName.blur(function(){
        alertName.hide();
    });
    
    txtName.focus(function(){
        validateName.removeClass('has-error');
        alertName.hide();
    });
    
    $('#bootstrapModal').on('hidden.bs.modal', function () {
        clear();
    });
    
    /* Clear Text in Modal */
    function clear(){
        alertId.hide();
        alertName.hide();
        $('#validate-id, #validate-name').removeClass('has-error');
        txtId.prop('readonly',false);
        txtId.val("");
        txtName.val("");
        cboGender.val("1");
        cboUniversity.val("AEU");
        cboClass.val("PP");
    }
    
    /* Validate Only Number */
    function isNumber (str){
        var patt = /^[0-9]*$/g;
        if (patt.test(str))
            return true;
        return false;
    }
});