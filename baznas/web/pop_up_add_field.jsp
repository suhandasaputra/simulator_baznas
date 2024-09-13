<%-- 
    Document   : pop_up_add_category
    Created on : Jan 03, 2020, 4:38:43 PM
    Author     : suhanda
--%>
<%--<%@include file='defaultextend.jsp' %>--%>
<style>
    .cd-buttons-add-field
    {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        font: inherit;
        vertical-align: baseline;
    }

    .img-replace-add-category {
        /* replace text with an image */
        display: inline-block;
        overflow: hidden;
        text-indent: 100%;
        color: transparent;
        white-space: nowrap;
    }

    .cd-popup-add-field {
        position: fixed;
        left: 0;
        top: 0;
        height: 100%;
        width: 100%;
        background-color: rgba(94, 110, 141, 0.9);
        opacity: 0;
        visibility: hidden;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0.3s;
        transition: opacity 0.3s 0s, visibility 0s 0.3s;
    }
    .cd-popup-add-field.is-visible-add-field {
        opacity: 1;
        visibility: visible;
        -webkit-transition: opacity 0.3s 0s, visibility 0s 0s;
        -moz-transition: opacity 0.3s 0s, visibility 0s 0s;
        transition: opacity 0.3s 0s, visibility 0s 0s;
    }

    .cd-popup-add-field-container {
        position: relative;
        width: 90%;
        height: -webkit-fill-available;
        max-width: 500px;
        margin: 4em auto;
        background: #FFF;
        border-radius: .25em .25em .4em .4em;
        text-align: center;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        -webkit-transform: translateY(-40px);
        -moz-transform: translateY(-40px);
        -ms-transform: translateY(-40px);
        -o-transform: translateY(-40px);
        transform: translateY(-40px);
        /* Force Hardware Acceleration in WebKit */
        -webkit-backface-visibility: hidden;
        -webkit-transition-property: -webkit-transform;
        -moz-transition-property: -moz-transform;
        transition-property: transform;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        transition-duration: 0.3s;
    }

    .cd-popup-add-field-container .cd-buttons-add-field:after {
        content: "";
        display: table;
        clear: both;
    }
    .cd-popup-add-field-container .cd-buttons-add-field li {
        float: left;
        width: 50%;
        list-style: none;
    }
    .cd-popup-add-field-container .cd-buttons-add-field div {
        cursor: pointer;
        display: block;
        height: 60px;
        line-height: 60px;
        text-transform: uppercase;
        color: #FFF;
        -webkit-transition: background-color 0.2s;
        -moz-transition: background-color 0.2s;
        transition: background-color 0.2s;
    }
    .cd-popup-add-field-container .cd-buttons-add-field li:first-child div {
        background: #3dceb7;
        border-radius: 0 0 0 .25em;
    }
    .cd-popup-add-field-container .cd-buttons-add-field li:first-child div:hover {
        background: #52e4cd;
        border-radius: 0 0 0 .25em;
    }

    .no-touch .cd-popup-add-field-container .cd-buttons-add-field li:first-child div:hover {
        background-color: #fc8982;
    }
    .cd-popup-add-field-container .cd-buttons-add-field li:last-child div {
        background: #b6bece;
        border-radius: 0 0 .25em 0;
    }
    .cd-popup-add-field-container .cd-buttons-add-field li:last-child div:hover {
        background: #d1d9e8;
        border-radius: 0 0 .25em 0;
    }
    .no-touch .cd-popup-add-field-container .cd-buttons-add-field li:last-child div:hover {
        background-color: #c5ccd8;
    }
    .cd-popup-add-field-container .cd-popup-add-field-close {
        position: absolute;
        top: 8px;
        right: 8px;
        width: 30px;
        height: 30px;
    }
    .cd-popup-add-field-container .cd-popup-add-field-close::before, .cd-popup-add-field-container .cd-popup-add-field-close::after {
        content: '';
        position: absolute;
        top: 12px;
        width: 14px;
        height: 3px;
        background-color: #8f9cb5;
    }
    .cd-popup-add-field-container .cd-popup-add-field-close::before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        left: 8px;
    }
    .cd-popup-add-field-container .cd-popup-add-field-close::after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        right: 8px;
    }
    .is-visible-add-field .cd-popup-add-field-container {
        -webkit-transform: translateY(0);
        -moz-transform: translateY(0);
        -ms-transform: translateY(0);
        -o-transform: translateY(0);
        transform: translateY(0);
        height: 400px;
    }

    @media only screen and (min-width: 1170px) {
        .cd-popup-add-field-container {
            margin: 8em auto;
        }
    }
    #label_add_category {
        width: 100%;
        margin: 20px;
        text-align: left;
    }
    #boxx_category{
        display: flex;
        justify-content: center;
        align-content: center;
    }
    #box1_category {
        cursor: pointer;
        width: 215px;
        height: 200px;
        margin-right: 5px;
        /*background-color: #68f3e9;*/
        border-radius: 5px;
    }
    #box2_category {
        width: 215px;
        height: 200px;
        /*margin-left: 5px;*/
        /*background-color: yellow;*/
    }
    input {
        padding-left: 10px;
        border: 1px solid #CCC; 
        border-radius: 3px;
        width: 100%;
        margin-bottom: 5px;
    }
    textarea {
        padding-left: 10px;
        border-radius: 3px;
        width: 100%;
        margin-top: 10px;
        height: 115px;
    }
    #buttonon_field {
        color: white;
        margin-top: 10px;
        display: flex;
    }
    #btn_cancel {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-right: 8px;
        border-radius: 3px;
    }
    #btn_save_add_field {
        cursor: pointer;
        width: 100px;
        background-color: #a7a0a0;
        border: 1px solid #CCC;
        margin-left: 8px;
        border-radius: 3px;
    }

    #btn_cancel:hover {
        background-color: #CCC;
    }
    #btn_save_add_field:hover {
        background-color: #CCC;
    }
    .image-upload > input {
        visibility:hidden;
        width:0;
        height:0
    }
    #panel_img {
        margin: 15px;
        height: 300px;
        width: 430px;
        background-color: white;
    }
    #blah {
        width: 430px;;
        height: 300px;
    }
    #btn_upl {
        width: 100px;
    }
    #img_filechooser_category {
        width: 215px;
        height: 200px;
    }
</style>


<script>
    jQuery(document).ready(function ($) {
        //open popup add category
//        $('#add_category_text').on('click', function (event) {
//            event.preventDefault();
//            $('.cd-popup-add-field').addClass('is-visible-add-field');
//        });

        //close popup add category
        $('.cd-popup-add-field').on('click', function (event) {
            if ($(event.target).is('.cd-popup-add-field-close') || $(event.target).is('.cd-popup-add-field') || $(event.target).is('#btn_cancel')) {
                event.preventDefault();
                $(this).removeClass('is-visible-add-field');
            }
        });
        //close popup when clicking the esc keyboard button add category
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup-add-field').removeClass('is-visible-add-field');
            }
        });
    });
</script>
<div class="cd-popup-add-field" role="alert">
    <div class="cd-popup-add-field-container">
        <label id="label_add_category">Add Field</label>
        <div id="boxx_category">
            <div id="box2_category">
                <div id="input_category">
                    <form action="lbs?action=addbank" id="form_add_bank" method="post">
                        <div id="myModal_add_field" class="modal">
                            <div class="modal-content">
                                <span class="close_add_field">&times;</span>
                                <div id="push_text_add_field"></div>
                            </div>
                        </div>
                        <label style="margin: 0px 0px 0px 5px; float: left; font-size: 14px">field id</label>
                        <input type="number" id="add_field_field" name="add_field_field" required="" placeholder="field">
                        <label style="margin: 0px 0px 0px 5px; float: left; font-size: 14px">name of field</label>
                        <input type="text" id="add_field_name_of_field" name="add_field_name_of_field" required="" placeholder="name of field" maxlength="50">
                        <label style="margin: 0px 0px 0px 5px; float: left; font-size: 14px">format</label>
                        <select name="add_field_format" id="add_field_format" style="
                                width: 215px;
                                border-radius: 3px;
                                margin-bottom: 6px;">
                            <option selected disabled>choose format data</option>
                            <option value="IF_CHAR">IF_CHAR</option>
                            <option value="IF_ECHAR">IF_ECHAR</option>
                            <option value="IF_NOP">IF_NOP</option>
                            <option value="IF_TBASE">IF_TBASE</option>
                            <option value="IF_TCHAR">IF_TCHAR</option>
                            <option value="IFA_AMOUNT">IFA_AMOUNT</option>
                            <option value="IFA_BINARY">IFA_BINARY</option>
                            <option value="IFA_BITMAP">IFA_BITMAP</option>
                            <option value="IFA_FLLCHAR">IFA_FLLCHAR</option>
                            <option value="IFA_FLLNUM">IFA_FLLNUM</option>
                            <option value="IFA_LBINARY">IFA_LBINARY</option>
                            <option value="IFA_LCHAR">IFA_LCHAR</option>
                            <option value="IFA_LLABINARY">IFA_LLABINARY</option>
                            <option value="IFA_LLBINARY">IFA_LLBINARY</option>
                            <option value="IFA_LLBNUM">IFA_LLBNUM</option>
                            <option value="IFA_LLCHAR">IFA_LLCHAR</option>
                            <option value="IFA_LLLABINARY">IFA_LLLABINARY</option>
                            <option value="IFA_LLLBINARY">IFA_LLLBINARY</option>
                            <option value="IFA_LLLCHAR">IFA_LLLCHAR</option>
                            <option value="IFA_LLLNUM">IFA_LLLNUM</option>
                            <option value="IFA_LLNUM">IFA_LLNUM</option>
                            <option value="IFA_NUMBERIC">IFA_NUMERIC</option>
                            <option value="IFA_TTLBINARY">IFA_TTLBINARY</option>
                            <option value="IFA_TTLCHAR">IFA_TTLCHAR</option>
                            <option value="IFA_TTLLBINARY">IFA_TTLLBINARY</option>
                            <option value="IFA_TTLLCHAR">IFA_TTLLCHAR</option>
                            <option value="IFA_TTLLLBINARY">IFA_TTLLBINARY</option>
                            <option value="IFA_TTLLLCHAR">IFA_TTLLLCHAR</option>
                            <option value="IFB_AMOUNT">IFB_AMOUNT</option>
                            <option value="IFB_BINARY">IFB_BINARY</option>
                            <option value="IFB_BITMAP">IFB_BITMAP</option>
                            <option value="IFB_FLLLNUM">IFB_FLLLNUM</option>
                            <option value="IFB_FLLNUM">IFB_FLLNUM</option>
                            <option value="IFB_FNUMERIC">IFB_FNUMERIC</option>
                            <option value="IFB_HEX">IFB_HEX</option>
                            <option value="IFB_LLBINARY">IFB_LLBINARY</option>
                            <option value="IFB_LLCHAR">IFB_LLCHAR</option>
                            <option value="IFB_LLHBINARY">IFB_LLHBINARY</option>
                            <option value="IFB_LLHCHAR">IFB_LLHCHAR</option>
                            <option value="IFB_LLHEX">IFB_LLHEX</option>
                            <option value="IFB_LLHFBINARY">IFB_LLHFBINARY</option>
                            <option value="IFB_LLHNUM">IFB_LLHNUM</option>
                            <option value="IFB_LLLBINARY">IFB_LLLBINARY</option>
                            <option value="IFB_LLLCHAR">IFB_LLLCHAR</option>
                            <option value="IFB_LLLHBINARY">IFB_LLLHBINARY</option>
                            <option value="IFB_LLLHCHAR">IFB_LLLHCHAR</option>
                            <option value="IFB_LLLHNUM">IFB_LLLHNUM</option>
                            <option value="IFB_LLLNUM">IFB_LLLNUM</option>
                            <option value="IFB_LLNUM">IFB_LLNUM</option>
                            <option value="IFB_NUMERIC">IFB_NUMERIC</option>
                        </select>
                        <!--<input type="text" id="add_field_format" name="add_field_format" required="" placeholder="format" maxlength="6">-->
<!--                        <label style="margin: 0px 0px 0px 5px; float: left; font-size: 14px">chars</label>
                        <select name="add_field_char" id="add_field_char" style="
                                width: 215px;
                                border-radius: 3px;
                                margin-bottom: 6px;">
                            <option value="a">A</option>
                            <option value="n">N</option>
                            <option value="an">AN</option>
                            <option value="ns">NS</option>
                            <option value="ans">ANS</option>
                            <option value="zpos">ZPOS</option>
                        </select>-->
                        <!--<input type="text" id="add_field_char" name="add_field_char" required="" placeholder="char" maxlength="4">-->
                        <label style="margin: 0px 0px 0px 5px; float: left; font-size: 14px">length</label>
                        <input type="number" id="add_field_length" name="add_field_length" required="" placeholder="length">
                    </form>
                    <div id="buttonon_field">
                        <div id="btn_cancel">Cancel</div>
                        <div id="btn_save_add_field">Save</div>
                    </div>

                    <div id="msgbox"></div>
                </div>
            </div>
        </div>
        <a href="#0" class="cd-popup-add-field-close img-replace-add-category">Close</a>
    </div>
</div>
<script>
    jQuery(document).ready(function ($) {


        $('#table_conn').on('click', 'td', function () {
            var $row = $(this).closest("tr");
            var bc = $row.find(".bankcode").text();

            $('#btn_save_add_field').unbind().bind('click', function (event) {
                var add_field_field = document.getElementById('add_field_field').value;
                var add_field_name_of_field = document.getElementById('add_field_name_of_field').value;
                var add_field_format = document.getElementById('add_field_format').value;
//                var add_field_char = document.getElementById('add_field_char').value;
                var add_field_length = document.getElementById('add_field_length').value;

                var modal = document.getElementById("myModal_add_field");
                var span = document.getElementsByClassName("close_add_field")[0];
                var push = document.getElementById("push_text_add_field");

                if (add_field_field == "") {
                    push.innerHTML = "<p>field must be Fill</p>";
                    modal.style.display = "block";
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                } else if (add_field_field != "") {
                    if (add_field_name_of_field == "") {
                        push.innerHTML = "<p>name of field must be fill</p>";
                        modal.style.display = "block";
                        span.onclick = function () {
                            modal.style.display = "none";
                        }
                        window.onclick = function (event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    } else if (add_field_name_of_field != "") {
                        if (add_field_format == "") {
                            push.innerHTML = "<p>name of field must be fill</p>";
                            modal.style.display = "block";
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        } else if (add_field_format != "") {
//                            if (add_field_char == "") {
//                                push.innerHTML = "<p>format must be fill</p>";
//                                modal.style.display = "block";
//                                span.onclick = function () {
//                                    modal.style.display = "none";
//                                }
//                                window.onclick = function (event) {
//                                    if (event.target == modal) {
//                                        modal.style.display = "none";
//                                    }
//                                }
//                            } else if (add_field_char != "") {
                                if (add_field_length == "") {
                                    push.innerHTML = "<p>length must be fill</p>";
                                    modal.style.display = "block";
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
                                    window.onclick = function (event) {
                                        if (event.target == modal) {
                                            modal.style.display = "none";
                                        }
                                    }
                                } else if (add_field_length != "") {
                                    var datjson9 = new Object();
                                    datjson9.field = add_field_field;
                                    datjson9.name_of_field = add_field_name_of_field;
                                    datjson9.format = add_field_format;
//                                    datjson9.char = add_field_char;
                                    datjson9.length = add_field_length;
                                    datjson9.bankcode = bc;

                                    $.ajax({
                                        contentType: 'application/json',
                                        dataType: "json",
                                        url: "adf",
                                        data: JSON.stringify(datjson9),
                                        type: 'post',
                                        success: function (response) {
                                            if (response.status == 00) {
                                                alert('success add field');
                                                $('#table_iso').DataTable().ajax.reload();
                                            } else {
                                                alert('failed add field');
                                            }
                                        }
                                    });
                                }
//                            }
                        }
                    }
                }
            });
        });
    });
</script>