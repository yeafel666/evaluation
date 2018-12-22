layui.define(['table', 'jquery', 'form'], function (exports) {
    "use strict";

    var MOD_NAME = 'tableSelect',
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;
    var tableSelect = function () {
        this.v = '1.0.1';
    };

    /**
    * 初始化表格选择器
    */
    tableSelect.prototype.render = function (opt) {

        var elem = $(opt.elem);

        //默认设置
        opt.searchKey = opt.searchKey || 'keyword';
        opt.searchPlaceholder = opt.searchPlaceholder || '关键词搜索';
        opt.table.page = opt.table.page || true;
        opt.table.height = opt.table.height || 315;

        elem.on('click', function(e) {
            e.stopPropagation();

            if($('div.tableSelect').length >= 1){
                return false;
            }

            var t = elem.offset().top + elem.outerHeight()+"px";
            var l = elem.offset().left +"px";
            var tableName = "tableSelect_table_" + new Date().getTime();
            var tableBox = '<div class="tableSelect layui-anim layui-anim-upbit" style="left:'+l+';top:'+t+';border: 1px solid #d2d2d2;background-color: #fff;box-shadow: 0 2px 4px rgba(0,0,0,.12);padding:10px 10px 0 10px;position: absolute;z-index: 100;margin: 5px 0;border-radius: 2px;">';
                tableBox += '<div class="tableSelectBar">';
                tableBox += '<form class="layui-form" action="" style="display:inline-block;">';
                tableBox += '<input style="display:inline-block;width:190px;height:30px;vertical-align:middle;margin-right:-1px;border: 1px solid #C9C9C9;" type="text" name="'+opt.searchKey+'" placeholder="'+opt.searchPlaceholder+'" autocomplete="off" class="layui-input"><button class="layui-btn layui-btn-sm layui-btn-primary tableSelect_btn_search" lay-submit lay-filter="tableSelect_btn_search"><i class="layui-icon layui-icon-search"></i></button>';
                tableBox += '</form>';
                tableBox += '<button style="float:right;" class="layui-btn layui-btn-sm tableSelect_btn_select">选择</button>';
                tableBox += '</div>';
                tableBox += '<table id="'+tableName+'" lay-filter="'+tableName+'"></table>';
                tableBox += '</div>';
                tableBox = $(tableBox);
            $('body').append(tableBox);

            //渲染TABLE
            opt.table.elem = "#"+tableName;
            var tableSelect_table = table.render(opt.table);

            //关键词搜索
            form.on('submit(tableSelect_btn_search)', function(data){
                tableSelect_table.reload({
                    where: data.field,
                    page: {
                      curr: 1
                    }
                  });
                return false;
            });

            //双击行选中
            table.on('rowDouble('+tableName+')', function(obj){
                var checkStatus = {data:[obj.data]};
                opt.done(elem, checkStatus);
                tableBox.remove();
            })

            //按钮选中
            tableBox.find('.tableSelect_btn_select').on('click', function() {
                var checkStatus = table.checkStatus(''+tableName+'');
                opt.done(elem, checkStatus);
                tableBox.remove();
            })
            
            //点击其他区域关闭
            $(document).mouseup(function(e){
                var userSet_con = $(''+opt.elem+',.tableSelect');
                if(!userSet_con.is(e.target) && userSet_con.has(e.target).length === 0){
                    tableBox.remove();
                }
            });
        })
    }

    /**
    * 隐藏选择器
    */
    tableSelect.prototype.hide = function (opt) {
        $('.tableSelect').remove();
    }

    //自动完成渲染
    var tableSelect = new tableSelect();

    //FIX 滚动时错位
    $(window).scroll(function () {
        tableSelect.hide();
    });

    exports(MOD_NAME, tableSelect);
})