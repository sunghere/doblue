(function ($) {
    /*리스트 불러오기 + 초기화*/
    var project_list_load = function () {
        var str = "";
        $.ajax({
            url: "/project",
            method: "get",
            success: function (data) {
                var str = '';
                $.each(data, function (index, val) {
                    str += '<div class="isotope-item col-md-4 col-sm-6 '+ val.category +'">'+
             	   		   '<div class="project-image" id="list-img'+val.seq+'"></div>'+
             	   		   '<div class="project-title">'+ val.title +'</div>'+
             	   		   '<div class="project-duration">'+ val.sdate +' ~ '+ val.edate +'</div>'+
                    	   '</div></div>';
                })
                $(".isotope-container").html(str);
            }, fail: function () {
                alert("실패")
            }

        })
    }

    /*초기 세팅*/
    project_list_load();
    
    /* 프로젝트 등록 */
    $(".write-btn").click(function() {
    	var check = true;
        var category = $('#w_category').attr("value");
        var sdate = $('#sdatepicker').val();
        var edate = $('#edatepicker').val();
        var title = $('#w_title').val();
        var content = CKEDITOR.instances.write_content.setData('');
        var img = imageParse(content);
        var url = $('#w_url').val();
        if (sdate == null || sdate == '' || edate == null || edate == '') {
        	$('#dateInput').css({'background-color': '##f2dede', 'border-color': '#ebccd1'});
        	check = false;
        }
        if (title == null || title == '') {
            $('#titleInput').css({'background-color': '##f2dede', 'border-color': '#ebccd1'});
            check = false;
        }
        if (content.length < 1) {
            $('#contentInput').css({'background-color': '##f2dede', 'border-color': '#ebccd1'});
            check = false;
        }
        var object = new Object();
        object.category = category;
        object.sdate = sdate;
        object.edate = edate;
        object.title = title;
        object.content = content;
        object.img = img;
        object.url = url;
        
        if (check == true) {
            $.ajax({
            	url: "/project",
                method: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(object),
                success: function (data) {
                    if (data.result == "SUCS") {
                    	$('#sdatepicker').val('');
                    	$('#edatepicker').val('');
                    	$('#w_title').val('');
                    	CKEDITOR.instances.write_content.getData();
                    	$(".write-btn").click();
                    } else {
                        alert("실패")

                    }
                }, fail: function () {
                    alert("실패")

                }
            })
        } else {
            showMsg("빈칸이 있어요 전부 입력해주세요");
        }
    });
    
    /*메인에 뜰 이미지 파싱*/
    imageParse = function(content) {
    	var contentArray = [];
        contentArray = content.split('alt="" src="');
        if (contentArray[1] != null && contentArray[1] != "") {
            var src = contentArray[1].split('"');
            return src[0];
        } else {

        }
        return "";
    }
    
    /*메인 이미지 로드*/
    imagePut = function (src_list, data) {
        $.each(data, function (index, val) {

            var src = src_list[index];
            $('#list-img' + val.seq).css({
                "background-image": 'url("' + src + '")',
                "-webkit-background-size": "cover",
                "-moz-background-size": "cover",
                "-o-background-size": "cover",
                "background-size": "cover",
                "background-position": "center"
            });
        })
    };
    
    /*카테고리 선택부분*/
    $('.categorySel').change(function () {
        $('#w_category').attr('value', $('.categorySel option:selected').text());
    });
    
    var dates = $("#sdatepicker, #edatepicker ").datepicker({
        dateFormat: "yy-mm-dd",
        yearSuffix: '년',
        showOtherMonths: true,
        yearRange: "2017:2022",
        monthNames: ["1월", "2월", "3월", "4월", "5월", "6월",
            "7월", "8월", "9월", "10월", "11월", "12월"],
        onSelect: function (selectedDate) {
            var option = this.id == "sdatepicker" ? "minDate" : "maxDate",
                instance = $(this).data("datepicker"),
                date = $.datepicker.parseDate(
                    instance.settings.dateFormat ||
                    $.datepicker._defaults.dateFormat,
                    selectedDate, instance.settings);
            dates.not(this).datepicker("option", option, date);
        }
    });

    /*todo_delete 변경 ajax*/

    var todo_delete = function (id) {

        $.ajax({
            url: "/api/todos/" + id,
            contentType: "application/json",
            method: "delete",
            success: function (data) {
                if (data.result == "SUCS") {
                    todo_list_load("");
                } else {
                    alert("실패")

                }
            }, fail: function () {
                alert("실패")

            }

        })
    }
    /*completed 변경 ajax*/
    var todo_complete = function (id, completed) {
        var object = new Object();
        object.completed = completed;
        $.ajax({
            url: "/api/todos/" + id,
            contentType: "application/json",
            data: JSON.stringify(object),
            method: "put",
            success: function (data) {

                if (data.result == "SUCS") {
                    todo_list_load("");
                } else {
                    alert("실패")

                }

            }

        })
    }
    /*filter css*/
    var filter_css_reset = function () {
        $('a').removeClass("selected");
    }

    /* Event */
    $(".new-todo").keypress(function (event) {
        if (event.which == '13') {
            event.preventDefault();
            todo_create();
        }
    });
    /* 체크박스 누를떄 */
    $('.todo-list').on("change", ".toggle", function () {
        var todo_id = $(this).attr("datasrc");
        var chkbox_isSel = $(this).prop("checked");
        if (chkbox_isSel) {/* 체크할경우*/
            todo_complete(todo_id, 1);
        } else { /* 체크 풀경우 */
            todo_complete(todo_id, 0);
        }
    })
    /* destory 누를때*/
    $('.todo-list').on("click", ".destroy", function () {
        var todo_id = $(this).attr("datasrc");


        todo_delete(todo_id);
    });

    /*filter*/
    $('.filters').on('click', 'a', function () {
        event.preventDefault();

        selector = $(this).attr('href').split('/')[1];
        if (selector != null) {
            filter_css_reset();
            $(this).addClass('selected');
            todo_list_load();
        } else {
            /* null일경우 default값 */
            selector = ""
        }

    })
    $('.clear-completed').click(function () {
        if ($('.filters li a.selected').attr('href').split('/')[1] == 'active') {
            /*만약 선택되있던 filter 가 active라면, 디폴트로 돌린다*/
            $('.filters li a[href="#/"]').click();
        }
        /*위의 이벤트가 일어나기 전까지 딜레이*/
        setTimeout("$('li.completed').find('.destroy').click()", 500);


    })

})
(jQuery)
