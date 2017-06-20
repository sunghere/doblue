(function ($) {
    /*리스트 불러오기 + 초기화*/
    var project_list_load = function () {
        var str = "";
        $.ajax({
            url: "project",
            method: "get",
            success: function (data) {
                var str = '';
                $.each(data, function (index, val) {
                    str += '<div class="isotope-item col-md-4 col-sm-6 '+ val.category +'">'+
             	   		   '<div class="project-image" id="list-img'+val.seq+'" style="background:url('+val.img+') no-repeat 100% center"></div>'+
             	   		   '<div class="project-title">'+ val.title +'</div>'+
             	   		   '<div class="project-duration">'+ val.sdate +' ~ '+ val.edate +'</div>'+
                    	   '</div></div>';
                });
                $(".isotope-container").html(str);
            }, fail: function () {
                alert("실패")
            }

        })
    };

    /*초기 세팅*/
    project_list_load();
    
    /* 프로젝트 등록 */
    $(".write-btn").click(function() {
    	var check = true;
        var category = $('#w_category').attr("value");
        var sdate = $('#sdatepicker').val();
        var edate = $('#edatepicker').val();
        var title = $('#w_title').val();
        var content = CKEDITOR.instances.write_content.getData();
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
        if ( content ==null || content == "" || content.length < 1) {
            $('#contentInput').css({'background-color': '##f2dede', 'border-color': '#ebccd1'});
            check = false;
        }
        var object = {};
        object.category = category;
        object.sdate = sdate;
        object.edate = edate;
        object.title = title;
        object.content = content;
        object.img = img;
        object.url = url;
        
        if (check == true) {
            $.ajax({
            	url: "project",
                method: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(object),
                success: function (data) {
                    if (data.result == "SUCS") {
                    	$('#sdatepicker').val('');
                    	$('#edatepicker').val('');
                    	$('#w_title').val('');
                    	CKEDITOR.instances.write_content.setData('');
                    	$(".write-btn").click();
                    } else {
                        alert("실패")

                    }
                }, fail: function () {
                    alert("실패")

                }
            })
        } else {
            show_message("빈칸이 있어요 전부 입력해주세요");
        }
    });
    
    /*메인에 뜰 이미지 파싱*/
    imageParse = function(content) {
    	var contentArray = [];
    	if(content!=null && content!="") {
    	    if(content.indexOf("<img")) {
                contentArray = content.split('alt="" src="');
                if (contentArray[1] != null && contentArray[1] != "") {
                    var src = contentArray[1].split('"');
                    return src[0];
                } else {

                }
            }
        }
        return "";
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
    
    $('.clear-completed').click(function () {
        if ($('.filters li a.selected').attr('href').split('/')[1] == 'active') {
            /*만약 선택되있던 filter 가 active라면, 디폴트로 돌린다*/
            $('.filters li a[href="#/"]').click();
        }
        /*위의 이벤트가 일어나기 전까지 딜레이*/
        setTimeout("$('li.completed').find('.destroy').click()", 500);


    });

    $(document).ready(function(e) {
        $('.with-hover-text, .regular-link').click(function(e){
            e.stopPropagation();
        });

        /***************
         * = Hover text *
         * Hover text for the last slide
         ***************/
        $('.with-hover-text').hover(
            function(e) {
                $(this).css('overflow', 'visible');
                $(this).find('.hover-text')
                    .show()
                    .css('opacity', 0)
                    .delay(200)
                    .animate(
                        {
                            paddingTop: '25px',
                            opacity: 1
                        },
                        'fast',
                        'linear'
                    );
            },
            function(e) {
                var obj = $(this);
                $(this).find('.hover-text')
                    .animate(
                        {
                            paddingTop: '0',
                            opacity: 0
                        },
                        'fast',
                        'linear',
                        function() {
                            $(this).hide();
                            $( obj ).css('overflow', 'hidden');
                        }
                    );
            }
        );

        var img_loaded = 0;
        var j_images = [];

        /*************************
         * = Controls active menu *
         * Hover text for the last slide
         *************************/
        $(function() {
            var pause = 10;
            $(document).scroll(function(e) {
                delay(function() {

                        var tops = [];

                        $('.story').each(function(index, element) {
                            tops.push( $(element).offset().top - 200 );
                        });

                        var scroll_top = $(this).scrollTop();

                        var lis = $('.nav > li');

                        for ( var i=tops.length-1; i>=0; i-- ) {
                            if ( scroll_top >= tops[i] ) {
                                menu_focus( lis[i], i+1 );
                                break;
                            }
                        }
                    },
                    pause);
            });
            $(document).scroll();
        });
    });

    var delay = (function(){
        var timer = 0;
        return function(callback, ms){
            clearTimeout (timer);
            timer = setTimeout(callback, ms);
        };
    })();

    menu_focus = function( element, i ) {
        if ( $(element).hasClass('active') ) {
            if ( i == 4 ) {
                if ( $('.navbar').hasClass('inv') == false )
                    return;
            } else {
                return;
            }
        }

        $('.menu-nav li').removeClass('active');
        $(element).addClass('active');

        var icon = $(element).find('.icon');


        var left_pos = icon.offset().left - $('.menu-nav').offset().left;
        var el_width = icon.width() + $(element).find('.text').width() + 10;

        $('.active-menu').stop(false, false).animate(
            {
                left: left_pos,
                width: el_width
            },
            1500,
            'easeInOutQuart'
        );
    };

    /*************
     * = Parallax *
     *************/
    jQuery(document).ready(function ($) {
        //Cache some variables
        var links = $('.nav').find('li');
        slide = $('.slide');
        button = $('.button');
        mywindow = $(window);
        htmlbody = $('html,body');

        //Create a function that will be passed a slide number and then will scroll to that slide using jquerys animate. The Jquery
        //easing plugin is also used, so we passed in the easing method of 'easeInOutQuint' which is available throught the plugin.
        function goToByScroll(dataslide) {
            var offset_top = ( dataslide == 1 ) ? '0px' : $('.slide[data-slide="' + dataslide + '"]').offset().top;

            htmlbody.stop(false, false).animate({
                scrollTop: offset_top
            }, 1500, 'easeInOutQuart');
        }

        //When the user clicks on the navigation links, get the data-slide attribute value of the link and pass that variable to the goToByScroll function
        links.click(function (e) {
            e.preventDefault();
            dataslide = $(this).attr('data-slide');
            goToByScroll(dataslide);
            $(".nav-collapse").collapse('hide');
        });

        //When the user clicks on the navigation links, get the data-slide attribute value of the link and pass that variable to the goToByScroll function
        $('.navigation-slide').click(function (e) {
            e.preventDefault();
            dataslide = $(this).attr('data-slide');
            goToByScroll(dataslide);
            $(".nav-collapse").collapse('hide');
        });
    });

    /***************
     * = Menu hover *
     ***************/
    jQuery(document).ready(function ($) {
        //Cache some variables
        var menu_item = $('.menu-nav').find('li');

        menu_item.hover(
            function(e) {
                var icon = $(this).find('.icon');

                var left_pos = icon.offset().left - $('.menu-nav').offset().left;
                var el_width = icon.width() + $(this).find('.text').width() + 10;

                var hover_bar = $('<div class="active-menu special-active-menu"></div>')
                    .css('left', left_pos)
                    .css('width', el_width)
                    .attr('id', 'special-active-menu-' + $(this).data('slide') );

                $('.active-menu').after( hover_bar );
            },
            function(e) {
                $('.special-active-menu').remove();
            }
        );
    });

    var show_message = function (str) {
        $('#my-message').html(str);

        $('#show-message-btn').click();
    };
})
(jQuery);
