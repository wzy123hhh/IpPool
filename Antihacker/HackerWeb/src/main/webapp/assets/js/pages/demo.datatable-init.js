$(document).ready(function() {
    "use strict";

    var datas;

    $.ajax({
        type: "post",
        url: "AttackRecord",
        data: {"date":new Date()},
        dataType: "json",
        async:false,
        success: function (response) {
            if(response == "failed"){
                alert("失败")
            }else{
                datas=response;
            }
        }
    });


    var a = $("#datatable-buttons").DataTable({
        data:datas,
        // columns: [
        //     { datas: 'title' },
        //     { datas: 'imageurl' },
        //     { datas: 'content' },
        //     { datas: 'times'},
        //     { datas: 'statu'}
        // ],
        lengthChange: !1,
        buttons: ["copy", "print"],
        language: {
            paginate: {
                previous: "<i class='mdi mdi-chevron-left'>",
                next: "<i class='mdi mdi-chevron-right'>"
            }
        },
        drawCallback: function() {
            $(".dataTables_paginate > .pagination").addClass("pagination-rounded")
        }
    });
    $("#selection-datatable").DataTable({
        select: {
            style: "multi"
        },
        language: {
            paginate: {
                previous: "<i class='mdi mdi-chevron-left'>",
                next: "<i class='mdi mdi-chevron-right'>"
            }
        },
        drawCallback: function() {
            $(".dataTables_paginate > .pagination").addClass("pagination-rounded")
        }
    }),
        a.buttons().container().appendTo("#datatable-buttons_wrapper .col-md-6:eq(0)")
});