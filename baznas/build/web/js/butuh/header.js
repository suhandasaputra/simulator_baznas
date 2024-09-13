/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $.getJSON('UserYabesServlet?action=Listuseryabes', {}, function (data) {
        var useryabes = [];
        var useryabessum = data.length;
        var useryabessum1 = "You have " + data.length + " notification";
        for (var i = 0; i < data.length; i++) {
            useryabes += '<li><a><div class="pull-left"><div class="person"><i class="fa fa-user fa-2x"></i></div></div><h4>' +
                    data[i].username
                    + '<small><i class="fa fa-clock-o"></i>' + data[i].activitastime + '</small></h4><p>' +
                    data[i].activitas + '</p></a></li>';
        }
        $("ul#useryabes").html(useryabes);
        $("span#useryabessum").html(useryabessum);
        $("li#useryabessum1").html(useryabessum1);
    });
});

