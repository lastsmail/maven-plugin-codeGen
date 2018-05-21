/** @namespace window.HAOHE.${clsName}Model */
var GeneModel = window.HAOHE.${clsName}Model ||{};
(function(){

    /**
     * 用户数据表格
     * @type {string}
     */
    let table = "#${clsName?uncap_first}Table";

    /**
     * 用户数据表格导航
     * @type {string}
     */
    let tableNav = "#${clsName?uncap_first}TableNav";
    /**
     * 表单
     * @type {string}
     */
    let form ="#${clsName?uncap_first}Form";
    /**
     * 用户模块模态框
     * @type {string}
     */
    let modal = "#${clsName?uncap_first}Modal";

    /**
     * 表单进行遮盖
     * @type {string}
     */
    let subModal = "#${clsName?uncap_first}Modal .row";


    let urlList = HAOHE.getCtx() + "/${clsName?uncap_first}/${clsName?uncap_first}s";
    let urlEdit = HAOHE.getCtx() + "/${clsName?uncap_first}/edit";


    this.refreshTable = function(){
        $(table).jqGrid().trigger("reloadGrid");
    }

    this.getSelectRow = function (){
        let rowId = $(table).jqGrid("getGridParam","selrow");
        let data = $(table).jqGrid("getRowData",rowId);
        return data;
    }



    /**
     * 初始化表格导航
     */
    this.initTableGridNav = function(){
        $(table).jqGrid('navGrid', tableNav, {
            search:true,
            edit: true,
            add: true,
            del: true,
            refresh:true,
            view:true

        } );
    }
<#import "common.ftl" as com>
    /**
     * 初始化数据表格
     */
    this.initTableGrid = function(){
        $(table).jqGrid({
            url :urlList,
            editurl:urlEdit,
            datatype : "json",
            mtype:"post",
            height:"100%",
            rownumbers: true,
            colModel: [

                <#list fields as f>
                    <#if (f_index == 0)>
                     { name: 'id', index: 'id', align: "center", width: 55,hidden:true },
                    <#elseif (f_has_next)>
                    { name: '<@com.coloum2Field f.name />', label:'${f.memo}',  index: '<@com.coloum2Field f.name />',  align: "center",width: 100,editable:true
                    ,searchoptions: { sopt:['EqualTo', 'NotEqualTo']}},

                    <#else >
                    { name: '<@com.coloum2Field f.name />', label:'${f.memo}', index: '<@com.coloum2Field f.name />', width: 80, align: "center",editable:false
                    ,searchoptions: { sopt:['EqualTo', 'NotEqualTo']}}
                    </#if>

                </#list>

            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: tableNav,
            sortname: 'id',
            repeatitems: false,
            viewrecords: true,
            sortorder: "asc",
            caption: "${clsName}列表",
            toolbar : [ true, "top" ],
            autowidth: true,
            jsonReader:{
                root: "list",  //数据模型
                page: "pageNum",//数据页码
                total: "pages",//数据总页码
                records: "total",//数据总记录数
                repeatitems: true,
                id: "id" //唯一标识
            },
            prmNames: {
                page:"page",
                rows:"rows",
                sort: "order",
                order: "orderType",
                search:"search",
                nd:"nd",
                id:"id",
                oper:"oper",
                editoper:"edit",
                addoper:"add",
                deloper:"del",
                subgridid:"id",
                npage: null,
                totalrows:"totalrows"
            }
        });


    }
    this.initTableGridListener = function(){
        $(modal).on("hidden.bs.modal", function() {
            $(modal).removeData("bs.modal");
            $(modal).find(".modal-content").children().remove();
        });
    }
    /**
     * 创建当前位置
     */
    this.createMarker = function(){

        let model = `<ul class="breadcrumb">
                        <li> <i class="fa fa-home"></i> <a href="/index">首页</a></li>
                        <li>${clsName}管理</li>
                        <div class="clearfix"> <h3 class="content-title pull-left">${clsName}管理界面</h3> </div>
                     </ul> `;

        $(".page-header").append(model);;


    }



    this.clearModal = function () {
        $(modal).modal("hide");

    }
    /**
     * 模块入口
     * @returns {HAOHE.${clsName}Modal}
     */
    this.init = function(){
        this.initTableGrid();
        this.initTableGridNav();
        this.initTableGridListener();
        HAOHE.initBoxTools();
        this.createMarker()
        return this;

    }


}).apply(${clsName}Model)