<%-- 
    Document   : dashboard
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <%@include file='defaultextend.jsp'%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>MIOS</title>
        <style>
            .card {
                background-color: white;
            }

            #table_detail th {
                background-color: #ACACAC;
                color: white;
                font-weight: 100;
                
            }
            
        </style>
        <script>
            $(document).ready(function () {
                $('#table_detail thead tr.filters th').each(function () {
                    var title = $(this).text();
                    if ($(this).hasClass("input-filter")) {
                        $(this).html('<input  style="width: 100px; font-size: 10px" name ="' + $.trim(title).replace(/ /g, '') + '" type="text" class = "form-control" placeholder="' + $.trim(title) + '" />');
                    } else if ($(this).hasClass("date-filter")) {
                        $(this).html('<div class="input-prepend input-group"><span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span><input type="text" style="width: 100px; font-size: 10px"name="' + $.trim(title).replace(/ /g, '') + '"  placeholder="' + $.trim(title) + '" class="form-control daterange"/></div>');
                    }
                });

                var table = $('#table_detail').DataTable({
                    "ajax": {
                        "url": "/baznas/rpts",
                        "type": "GET",
                        "dataSrc": "",
                        "contentType": "application/json"
                    },
                    "columns": [

                        {data: "requesttime"},
                        {data: "noref"},
                        {data: "productcode"},
                        {data: "custno"},
                        {data: "fromaccount"},
                        {data: "amount"},
                        {data: "merchanttype"}
                    ],
                    dom: 'Bfrtip',
                    buttons: [
                        {
                            extend: 'collection',
                            text: 'Export',
                            buttons:
                                    [
                                        {
                                            extend: "copyHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'}, //last column has the action types detail/edit/delete
                                            footer: true
                                        },
                                        {
                                            extend: "csvHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "excelHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "pdfHtml5",
                                            title: "Report",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        },
                                        {
                                            extend: "print",
                                            exportOptions: {columns: ':visible:not()'},
                                            footer: true
                                        }
                                    ]
                        }
                    ]
                });
                $('.daterange').daterangepicker({
                    ranges: {
                        "Today": [moment(), moment()],
                        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                        '7 last days': [moment().subtract(6, 'days'), moment()],
                        '30 last days': [moment().subtract(29, 'days'), moment()],
                        'This month': [moment().startOf('month'), moment().endOf('month')],
                        'Last month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
                        'Blank date': [moment("0001-01-01"), moment("0001-01-01")]
                    }
                    ,
                    autoUpdateInput: false,
                    opens: "left",
                    locale: {
                        cancelLabel: 'Clear',
                        format: 'DD-MMM-YYYY'
                    }
                });
                var startDate;
                var endDate;
                var dataIdx;  //current data column to work with
                $("#transaction_wrapper thead").on("mousedown", "th", function (event) {
                    var visIdx = $(this).parent().children().index($(this));
                    dataIdx = table.column.index('fromVisible', visIdx);
                });
                // Function for converting a dd/mmm/yyyy date value into a numeric string for comparison (example 01-Dec-2010 becomes 20101201
                function parseDateValue(rawDate) {

                    var d = moment(rawDate, "DD-MMM-YYYY").format('DD-MM-YYYY');
                    var dateArray = d.split("-");
                    var parsedDate = dateArray[2] + dateArray[1] + dateArray[0];
                    return parsedDate;
                }
                //filter on daterange
                $(".daterange").on('apply.daterangepicker', function (ev, picker) {
                    ev.preventDefault();
                    //if blank date option was selected
                    if ((picker.startDate.format('DD-MMM-YYYY') == "01-Jan-0001") && (picker.endDate.format('DD-MMM-YYYY')) == "01-Jan-0001") {
                        $(this).val('Blank');


                        val = "^$";

                        table.column(dataIdx)
                                .search(val, true, false, true)
                                .draw();

                    } else {
                        //set field value
                        $(this).val(picker.startDate.format('DD-MMM-YYYY') + ' to ' + picker.endDate.format('DD-MMM-YYYY'));

                        //run date filter
                        startDate = picker.startDate.format('DD-MMM-YYYY');
                        endDate = picker.endDate.format('DD-MMM-YYYY');

                        var dateStart = parseDateValue(startDate);
                        var dateEnd = parseDateValue(endDate);

                        var filteredData = table
                                .column(dataIdx)
                                .data()
                                .filter(function (value, index) {

                                    var evalDate = value === "" ? 0 : parseDateValue(value);
                                    if ((isNaN(dateStart) && isNaN(dateEnd)) || (evalDate >= dateStart && evalDate <= dateEnd)) {

                                        return true;
                                    }
                                    return false;
                                });


                        var val = "";
                        for (var count = 0; count < filteredData.length; count++) {

                            val += filteredData[count] + "|";
                        }

                        val = val.slice(0, -1);


                        table.column(dataIdx)
                                .search(val ? "^" + val + "$" : "^" + "-" + "$", true, false, true)
                                .draw();
                    }
                });
                $(".daterange").on('cancel.daterangepicker', function (ev, picker) {
                    ev.preventDefault();
                    $(this).val('');
                    table.column(dataIdx)
                            .search("")
                            .draw();





                });
                //hide unnecessary columns
                var column = table.columns($('.HideColumn'));
                // Toggle the visibility
                column.visible(!column.visible());
                // Apply the search
                $.each($('.input-filter', table.table().header()), function () {
                    var column = table.column($(this).index());
                    //onsole.log(column);
                    $('input', this).on('keyup change', function () {
                        if (column.search() !== this.value) {
                            column
                                    .search(this.value)
                                    .draw();
                        }
                    });
                });
                $.fn.dataTable.ext.errMode = 'none';

            });
        </script> 

    </head>
    <body class="hold-transition sidebar-mini">
        <div class="wrapper">
            <!-- Navbar -->
            <%@include file='header.jsp'%>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <%@include file='sidebar_left.jsp'%>
            <!--end sidebar-->
            <div class="content-wrapper">
                <div class="content-header">
                    <section class="content">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <p class="text-left" style="color: #29B19C; font-size: 20px;">
                                                        Report
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="tab-content" style="width: 50%">
                                                    <div id="detail" class="tab-pane fade in active show">
                                                        <div class="container" id="ref_tabel_detail">        
                                                            <table class="table" id="table_detail" style="font-size: 12px;">
                                                                <thead>
                                                                    <tr>
                                                                        <th>request time</th>
                                                                        <th>noref</th>
                                                                        <th>product</th>
                                                                        <th>muzaki</th>
                                                                        <th>phonenumber</th>
                                                                        <th>amount</th>
                                                                        <th>merchant</th>
                                                                    </tr>
                                                                    <tr class="filters">
                                                                        <th class="date-filter">request time</th>
                                                                        <th class="input-filter">noref</th>
                                                                        <th class="input-filter">product</th>
                                                                        <th class="input-filter">muzaki</th>
                                                                        <th class="input-filter">phonenumber</th>
                                                                        <th class="input-filter">amount</th>
                                                                        <th class="input-filter">merchant</th>
                                                                    </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>    
                                            </div>
                                            <!-- /.row -->
                                        </div>
                                        <!-- ./card-body -->
                                    </div>
                                    <!-- /.card -->
                                </div>
                                <!-- /.col -->
                            </div>
                            <!-- /.row -->
                        </div>
                    </section>
                </div>
            </div>
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- Main Footer -->
            <%@include file='footer.jsp'%>
        </div>
    </body>
</html>
