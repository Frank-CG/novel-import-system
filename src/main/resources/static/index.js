console.log("ABC");
var table; // use a global for the submit and return data rendering in the examples
var json_result;
$(document).ready(function() {
    $("form#data").submit(function(e) {
        e.preventDefault();
        var formData = new FormData();
        formData.append('mode', $('#mode').val());
        formData.append('file', this[0].files[0]);

        $.ajax({
            url: 'api/v1/novel/fileImport',
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function (json,status) {
                json_result = json;
                $('#import_result').html(JSON.stringify(json, undefined, 2));
                if(json.code == 200){
                    $('#import_result').removeClass('text-danger').addClass('text-success');
                    reloadDataTable();
                }else{
                    $('#import_result').removeClass('text-success').addClass('text-danger');
                }
            },
            cache: false,
            contentType: false,
            processData: false
        });
    });

    function importBtnOnClick() {
        var f = $('#data_file').val();
        if(f == ""){
            alert("Please choose data file first");
            return;
        }
        $("form#data").submit();
    }
    $('#importBtn').on('click',importBtnOnClick);

    function reloadDataTable() {
        if(table !== null){
            table.destroy();
        }
        $.ajax({
            dataType: 'json',
            url: "/api/v1/novel",
            method: "GET",
            success: function(resp){
                console.log(resp);
                table = $('#example').DataTable( {
                    data: resp,
                    columns: [
                        {title:"Novel Id", data: "id", visible: false},
                        {title:"Writer Id", data:"writerId"},
                        {title:"Novel Name", data:"novelName"},
                        {title:"Novel Type", data:"novelType"},
                        {title:"Novel Status", data:"novelStatus"},
                        {title:"Novel Desc", data:"novelDesc"}
                    ]
                } );
            }
        });
    }

    function initDataTable() {
        $.ajax({
            dataType: 'json',
            url: "/api/v1/novel",
            method: "GET",
            success: function(resp){
                console.log(resp);
                table = $('#example').DataTable( {
                    data: resp,
                    columns: [
                        {title:"Novel Id", data: "id", visible: false},
                        {title:"Writer Id", data:"writerId"},
                        {title:"Novel Name", data:"novelName"},
                        {title:"Novel Type", data:"novelType"},
                        {title:"Novel Status", data:"novelStatus"},
                        {title:"Novel Desc", data:"novelDesc"}
                    ]
                } );

                $('#example tbody').on( 'click', 'tr', function () {
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }
                    else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                    editFormUpdate();
                } );
            }
        });
    }

    function editFormUpdate() {
        var row_data = table.row($('tr.selected')).data();
        $('#novel_id').val(row_data.id);
        $('#writer_id').val(row_data.writerId);
        $('#novel_name').val(row_data.novelName);
        $('#novel_type').val(row_data.novelType);
        $('#novel_status').val(row_data.novelStatus);
        $('#novel_desc').val(row_data.novelDesc);
    }

    $('#resetBtn').on('click',function () {
        $('#novel_id').val('');
        $('#writer_id').val('');
        $('#novel_name').val('');
        $('#novel_type').val('');
        $('#novel_status').val('');
        $('#novel_desc').val('');
    });

    $('#deleteBtn').on('click',function () {
        var novel_id = $('#novel_id').val();
        if(novel_id !== ""){
            $.ajax({
                dataType: 'text',
                url: "/api/v1/novel/" + novel_id,
                method: "DELETE",
                success: function (json, status) {
                    table.row('tr.selected').remove().draw(false);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("Status: " + textStatus); alert("Error: " + errorThrown);
                }
            });
        }else{
            alert("Please select one record before delete operation.");
        }
    });

    $('#updateBtn').on('click',function () {
        var novel_id = $('#novel_id').val();
        var novel = {};
        if(novel_id == ""){
            alert("Please select one record before update its content.")
        }else {
            novel = {
                "id":$('#novel_id').val(),
                "writerId":$('#writer_id').val(),
                "novelName":$('#novel_name').val(),
                "novelType":$('#novel_type').val(),
                "novelStatus":$('#novel_status').val(),
                "novelDesc":$('#novel_desc').val()
            };
            console.log(novel);
            $.ajax({
                url: "/api/v1/novel/",
                method: "POST",
                data: JSON.stringify(novel),
                dataType: 'json',
                contentType: 'application/json',
                success: function (json, status) {
                    if(status == "success"){
                        $('#edit_result').html(JSON.stringify(json, undefined, 2));
                        if(json.code == 200){
                            $('#edit_result').removeClass('text-danger').addClass('text-success');
                            reloadDataTable();
                        }else{
                            $('#edit_result').removeClass('text-success').addClass('text-danger');
                        }
                    }else{
                        alert("Server Error.")
                    }
                }
            });
        }
    });

    $('#createBtn').on('click',function () {
        var novel_id = $('#novel_id').val();
        var novel = {};
        if(novel_id !== ""){
            alert("Please reset this form before create a new record.")
        }else {
            novel = {
                "writerId":$('#writer_id').val(),
                "novelName":$('#novel_name').val(),
                "novelType":$('#novel_type').val(),
                "novelStatus":$('#novel_status').val(),
                "novelDesc":$('#novel_desc').val()
            };
            console.log(novel);
            $.ajax({
                url: "/api/v1/novel/",
                method: "POST",
                data: JSON.stringify(novel),
                dataType: 'json',
                contentType: 'application/json',
                success: function (json, status) {
                    if(status == "success"){
                        $('#edit_result').html(JSON.stringify(json, undefined, 2));
                        if(json.code == 200){
                            $('#edit_result').removeClass('text-danger').addClass('text-success');
                            reloadDataTable();
                        }else{
                            $('#edit_result').removeClass('text-success').addClass('text-danger');
                        }
                    }else{
                        alert("Server Error.")
                    }
                }
            });
        }
    });

    initDataTable();
} );