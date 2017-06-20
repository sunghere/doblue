/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here.
    // For complete reference see:
    // http://docs.ckeditor.com/#!/api/CKEDITOR.config
    config.language = 'ko';   //언어설정
    config.uiColor = '#EEEEEE';  //ui 색상

    /* plugin*/
    config.extraPlugins = 'youtube';

    config.extraPlugins = 'autogrow';
    config.autoGrow_minHeight = 400;
    config.autoGrow_maxHeight = 1200;
    config.autoGrow_bottomSpace = 50;
    config.font_defaultLabel = 'Gulim';
    config.font_names = '맑은 고딕; 돋움; 바탕; 돋음; 궁서; Nanum Gothic Coding; Quattrocento Sans;' + CKEDITOR.config.font_names;
    config.fontSize_defaultLabel = '12px';
    config.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;';
    /* config.enterMode =CKEDITOR.ENTER_BR;  //엔터키 입력시 br 태그 변경
     config.shiftEnterMode = CKEDITOR.ENTER_P; //엔터키 입력시 p 태그로 변경
     config.contentsCss = ['/css/style.css'], ['/css/main.css']; //홈페이지에서 사용하는 Css 파일 인클루드
     */
    // The toolbar groups arrangement, optimized for two toolbar rows.
    config.toolbarGroups = [
        {name: 'clipboard', groups: ['clipboard', 'undo']},
        {name: 'editing', groups: ['find', 'selection', 'spellchecker']},
        {name: 'links'},
        {name: 'insert'},
        {name: 'forms'},
        {name: 'tools'},
        {name: 'document', groups: ['mode', 'document', 'doctools']},
        {name: 'others'},
        '/',
        {name: 'basicstyles', groups: ['basicstyles', 'cleanup']},
        {name: 'paragraph', groups: ['list', 'indent', 'blocks', 'align', 'bidi']},
        {name: 'styles'},
        {name: 'colors'},
        {name: 'about'}
    ];

    // Remove some buttons provided by the standard plugins, which are
    // not needed in the Standard(s) toolbar.
    config.removeButtons = 'Underline,Subscript,Superscript,Source';

    // Set the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Simplify the dialog windows.
    config.removeDialogTabs = 'image:advanced;link:advanced';

};
