(function ($) {
    /*리스트 불러오기 + 초기화*/
    var project_list_load = function () {
        $.ajax({
            url: "project",
            method: "get",
            success: function (data) {
                var str = '';
                $.each(data, function (index, val) {
                    str += '<div class="isotope-item col-md-4 col-sm-6 ' + val.category + '" data-seq="' + val.seq + '">' +
                        '<a href="#" data-target="#detailModal" data-toggle="modal">' +
                        '<img class="project-image" src="' + val.img + '">' +
                        '<div class="overlay">' +
                        '<div class="project-title">' + val.title + '<br>' +
                        '<span class="project-duration">' + val.sdate + ' ~ ' + val.edate + '</span></div>' +
                        '</div></a></div>';
                });
                $(".isotope-container").html(str);

                list_filter('');
                nextIcon_show();

            }, fail: function () {
                alert("실패")
            }

        })
    };

    /*메인 리스트 필터링*/
    list_filter = function (filter) {
        var data = $(".isotope-item").filter(filter + ":visible");
        $.each(data, function (index, val) {
            var me = $(this);
            me.attr("data-index", index);


            if (index >= 9) {
                me.css({"display": "none", "position": "absolute"});
                me.attr('my-filter', "filter");

            } else {
                me.css({"display": "", "position": "relative"});

                me.css({"top": "", "bottom": "", "left": "", "right": ""})

            }
        })
    };
    var cleanTransitionStyle = {
        transitionProperty: '',
        transitionDuration: '',
        transitionDelay: '',
        transform: '',
        transition: ''
    };
    /*프로젝트 다음리스트*/
    $(".icon-next").click(function () {
        var data = $(".isotope-item");
        var last = data.filter(":visible:last").attr("data-index");
        $.each(data, function (index, val) {
            if (last % 8 != 0) {
                if (index >= 0 && index < 9) {
                    $(val).css({"display": "", "position": "relative"});
                    $(this).attr('my-filter', "");

                } else {
                    $(val).css({"display": "none", "position": "absolute"});
                    $(this).attr('my-filter', "filter");

                }
            } else {
                if (index > last && index < last + 9) {
                    $(val).css({"display": "", "position": "relative"});
                    $(this).attr('my-filter', "");

                } else {
                    $(val).css({"display": "none", "position": "absolute"});
                    $(this).attr('my-filter', "filter");
                }

                $(val).css({"top": "", "bottom": "", "left": "", "right": ""})
            }

        });
    });

    /*화살표 보이기*/
    nextIcon_show = function () {
        var visible = $(".isotope-item").filter(":visible");
        var hidden = $(".isotope-item").filter(":hidden");

        var height = $(".portfolio").height();
        var itemHeight = $(".isotope-item").height();
        var list = visible.length;

        if (visible.length >= 9 && hidden.length > 0) {
            // $(".icon-next").css("display", "block")
            $(".icon-next").fadeIn();
        } else {
            // $(".icon-next").css("display", "none")
            $(".icon-next").fadeOut();
        }

        if ($(window).width() < 768) {
            $(".isotope-container").css("height", list * itemHeight);
        } else if ($(window).width() < 992) {
            $(".isotope-container").css("height", Math.ceil(list / 2) * itemHeight);
        } else {
            $(".portfolio").css("height", height);
        }

    };

    /*초기 세팅*/
    project_list_load();

    /*포트폴리오 클릭 필터링*/
    $('.isotope-nav li').click(function () {
        var data = $(".isotope-item");
        $.each(data, function (index, val) {
            var me = $(val);

            me.attr("data-index", "");

            if (me.attr('my-filter') == 'filter') {
                me.css({"display": "", "position": "relative"});

                me.attr("my-filter", "");

            }

        });

        var me = $(this);
        var filterValue = me.attr('data-filter');
        $(".isotope-container").isotope({filter: filterValue});

        if (filterValue == '*') {
            setTimeout("list_filter('')", 500);

            setTimeout("nextIcon_show();", 600);

        } else {

            setTimeout("list_filter('" + filterValue + "');", 500);

            setTimeout("nextIcon_show();", 600);

        }
    });

    /*프로젝트 디테일 클릭*/
    $(".isotope-container").on("click", ".isotope-item", function () {
        var seq = $(this).attr('data-seq');
        project_detail_load(seq);
    });

    /* 총 작업일 */
    var project_period = function (sdate, edate) {
        var sdateArray = sdate.split("-");
        var edateArray = edate.split("-");

        var sdateObj = new Date(sdateArray[0], Number(sdateArray[1]) - 1, sdateArray[2]);
        var edateObj = new Date(edateArray[0], Number(edateArray[1]) - 1, edateArray[2]);

        var between = Math.floor((edateObj.getTime() - sdateObj.getTime()) / 1000 / 60 / 60 / 24);
        if (between < 0) return 0;
        else return between;
    };

    /*프로젝트 디테일*/
    var project_detail_load = function (seq) {
        $.ajax({
            url: "project/" + seq,
            method: "get",
            success: function (data) {
                var str1 = '<div class="title">' + data.title + '</div>' +
                    '<div class="duration">' + data.sdate + ' ~ ' + data.edate +
                    ', 총 작업일: ' + project_period(data.sdate, data.edate) + '일</div>';
                var str2 = '<div class="content">' + data.content + '</div>' +
                    '<div class="url">' + data.url + '</div>';
                $(".detail-title").html(str1);
                $(".detail-body").html(str2);
                $(".content img").attr("style", "width:100%; height:auto;");

            }, fail: function () {
                alert("실패")
            }

        })
    };

    /* 프로젝트 등록 */
    $(".write-btn").click(function () {
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
        if (content == null || content == "" || content.length < 1) {
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

        if (check === true) {
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
                        $(".close").click();
                        project_list_load();
                    } else {
                        show_message("실패")

                    }
                }, fail: function () {
                    show_message("실패")


                }
            });


        } else {
            show_message("빈칸이 있어요 전부 입력해주세요");
        }
    });

    /*메인에 뜰 이미지 파싱*/
    imageParse = function (content) {
        var contentArray = [];
        if (content != null && content != "") {
            if (content.indexOf("<img")) {
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

    /*리스트 필터링 버튼 클릭*/
    $(".isotope-nav li").click(function () {
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });

    /*카테고리 선택부분*/
    $('.categorySel').change(function () {
        $('#w_category').attr('value', $('.categorySel option:selected').text());
    });

    /*데이트피커*/
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

    $(document).ready(function (e) {
        $('.with-hover-text, .regular-link').click(function (e) {
            e.stopPropagation();
        });

        /***************
         * = Hover text *
         * Hover text for the last slide
         ***************/
        $('.with-hover-text').hover(
            function (e) {
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
            function (e) {
                var obj = $(this);
                $(this).find('.hover-text')
                    .animate(
                        {
                            paddingTop: '0',
                            opacity: 0
                        },
                        'fast',
                        'linear',
                        function () {
                            $(this).hide();
                            $(obj).css('overflow', 'hidden');
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
        $(function () {
            var pause = 10;
            $(document).scroll(function (e) {
                delay(function () {

                        var tops = [];

                        $('.story').each(function (index, element) {
                            tops.push($(element).offset().top - 200);
                        });

                        var scroll_top = $(this).scrollTop();

                        var lis = $('.nav > li');

                        for (var i = tops.length - 1; i >= 0; i--) {
                            if (scroll_top >= tops[i]) {
                                menu_focus(lis[i], i + 1);
                                break;
                            }
                        }
                    },
                    pause);
            });
            $(document).scroll();
        });
    });

    var delay = (function () {
        var timer = 0;
        return function (callback, ms) {
            clearTimeout(timer);
            timer = setTimeout(callback, ms);
        };
    })();

    menu_focus = function (element, i) {
        if ($(element).hasClass('active')) {
            if (i == 4) {
                if ($('.navbar').hasClass('inv') == false)
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
            function (e) {
                var icon = $(this).find('.icon');

                var left_pos = icon.offset().left - $('.menu-nav').offset().left;
                var el_width = icon.width() + $(this).find('.text').width() + 10;

                var hover_bar = $('<div class="active-menu special-active-menu"></div>')
                    .css('left', left_pos)
                    .css('width', el_width)
                    .attr('id', 'special-active-menu-' + $(this).data('slide'));

                $('.active-menu').after(hover_bar);
            },
            function (e) {
                $('.special-active-menu').remove();
            }
        );
    });

    var show_message = function (str) {
        $('#my-message').html(str);

        $('#show-message-btn').click();
    };

    $('.send-mail button').click(function () {
        mailSend();
    })
    function mailSend() {
        var email = $('#email').val();
        var name = $('#name').val();
        var content = $('#message').val();


        var blank_pattern = /[\s]/g;
        var email_pattern = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

        if (email.length < 5 || blank_pattern.test(email) || !(email_pattern.test(email))) {

            show_message("Oops.. Email Check plz")

            return false;
        }
        if (name.length < 3 || blank_pattern.test(name)) {
            show_message("Oops.. name Check plz");
            return false;
        }

        if (content.length < 10) {
            show_message("Oops.. write content plz")
            return false;
        }


        $.ajax({
            url: "email",
            method: "post",
            dataType: 'text json', // JSON 타입이 아닐경우 제거
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                'title': "Doblue portfolio 사이트 메일<" + name + ">", "email": email, 'content': content
            }), success: function (data) {

                if (data.result == "SUCS") {
                    show_message("[Success]Thanks - ");
                    $('#content').val('')
                } else {

                    show_message("[Error]try later..")
                }

            }
        })
    }
})
(jQuery);
