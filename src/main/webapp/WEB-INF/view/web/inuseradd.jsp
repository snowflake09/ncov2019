<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>返京人员登记</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport"/>
    <link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${ctx }/image/icon/logo.ico"/>
    <!-- CSS -->
    <link rel="stylesheet" href="${ctx }/js/ncov/inuser.css"/>
    <!-- Javascript -->
    <script src="${ctx }/js/jquery/jquery.min.js"></script>
    <script src="${ctx }/js/common/MD5_UTF8.js"></script>
    <script src="${ctx }/js/common/layer/layer.js"></script>
    <script src="${ctx }/js/common/app/util.js"></script>
<%--    <link href="${ctx }/js/bootstrap/bootstrap.min.css" rel="stylesheet">--%>

    <script>
        //如果登录窗口不在主窗口，在跳转到主窗口
        if (window != top) {
            top.location.href = "login";
        }
        jQuery(document)
            .ready(
                function () {
                    //回车事件绑定
                    document.onkeydown = function (event) {
                        var e = event || window.event
                            || arguments.callee.caller.arguments[0];
                        if (e && e.keyCode == 13) {
                            $('#submit').click();
                        }
                    };

                    //登录操作
                    $('#submit')
                        .click(
                            function () {
                                var openid = $('.openid').val();
                                var username = $('.username').val();
                                var phone = $('.phone').val();
                                var card_no = $('.card_no').val();
                                var sex = $('.sex').val();
                                var provice_name = $('.provice_name').val();
                                var city_name = $('.city_name').val();
                                var area_name = $('.area_name').val();
                                var regionid = $('.regionid').val();
                                var building = $('.building').val();
                                var region_adress = $('.region_adress').val();
                                var trip_no = $('.trip_no').val();
                                var healthy = $('.healthy').val();
                                var health_note = $('.health_note').val();
                                var carno = $('.carno').val();
                                var temperature = $('.temperature').val();
                                if (username == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '20px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }

                                if (card_no == '' || !isCardNo(card_no)) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '80px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.card_no')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (phone == '' || !checkPhone(phone)) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '140px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.phone')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (provice_name == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '210px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (building == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '270px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (region_adress == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '330px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (city_name == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '390px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (area_name == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '450px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (sex == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '510px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (trip_no == '' && (sex == 1 || sex == 2)) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '580px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (healthy == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '640px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (health_note == '') {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '700px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (carno == '' && sex == 0) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '760px')
                                                .show();
                                        });
                                    $('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.username')
                                                .focus();
                                        });
                                    return false;
                                }
                                if (temperature == '' || temperature < 36.0 || temperature > 45.0) {
                                    $('.error').fadeOut(
                                        'fast',
                                        function () {
                                            $('.error').css(
                                                'top',
                                                '820px')
                                                .show();
                                        });
                                    $(this).find('.error').fadeIn(
                                        'fast',
                                        function () {
                                            $('.temperature')
                                                .focus();
                                        });
                                    return false;
                                }
                                var data = {
                                    WECHATID:openid,
                                    NAME: username,
                                    PHONE: phone,
                                    CARDNO: card_no,
                                    SEX: sex,
                                    PROVINCENAME: provice_name,
                                    CITYNAME: city_name,
                                    AREANAME: area_name,
                                    USERTYPE:3,
                                    REGIONID: regionid,
                                    REGIONADDRESS: building+"-"+region_adress,
                                    TEMPERATURE: temperature,
                                    HEALTHY: healthy,
                                    CARNO: carno,
                                    TRIPNO: trip_no,
                                    HEALTHNOTE: health_note
                                };
                                var load = layer.load();
                                $.post(
                                    "${ctx}/ncov/web/addinuser",
                                    data,
                                    function (result) {
                                        layer
                                            .close(load);
                                        if (result.success
                                            && result.status != 200) {
                                            layer.msg('感谢您的配合，您登记的信息为，姓名：'+data.NAME+'，身份证：'+data.CARDNO+'，联系电话：'+data.PHONE+'，体温：'+data.TEMPERATURE+'度', {
                                                    time: 60000,
                                                    area:['auto', 'auto'],
                                                    anim:0,
                                                    shade:[0.5, '#393D49'],
                                                    btn: ['继续']
                                                },
                                                function () {
                                                });
                                            $('.username').val('');
                                            $('.phone').val('');
                                            $('.card_no').val('');
                                            $('.error').fadeOut();
                                            return;
                                        } else {
                                            layer.msg(result.msg, {
                                                    time: 60000,
                                                    area:['auto', 'auto'],
                                                    anim:0,
                                                    shade:[0.5, '#393D49'],
                                                    btn: ['继续']
                                                },
                                                function () {
                                                });
                                            $('input').val('');
                                            $('.phone').val('');
                                            $('.card_no').val('');
                                            $('.error').fadeOut();
/*                                            setTimeout(
                                                function () {
                                                    //登录返回
                                                    window.location.href = '${ctx}'
                                                        + result.back_url
                                                        || "/";
                                                },
                                                1000)*/
                                        }
                                    }, "json");

                            });
                    $(
                        '.page-container form .username, .page-container form .password')
                        .keyup(
                            function () {
                                $(this).parent().find('.error')
                                    .fadeOut('fast');
                            });
                });
    </script>
    <style>
        canvas {
            position: fixed;
            top: 0px;
            left: 0px;
        }
    </style>
</head>

<body id="body">
<div class="page-container">
    <h1>返京人员登记</h1>
    <div>${now}</div>
    <form id="_form" action="" method="post">
        <input name="openid" class="openid" type="hidden" value="${openid}">
        <input name="regionid" class="regionid" type="hidden" value="${regionid}">
        <input type="text" name="username" class="username" placeholder="姓名" maxlength="10">
        <input type="text" name="card_no" class="card_no" placeholder="18位身份证号码" maxlength="18">
        <input type="text" name="phone" class="phone" placeholder="联系方式：11位电话号码" maxlength="11">
        <input type="text" name="provice_name" class="provice_name" placeholder="户口所在地" maxlength="10">
        <select name="building" class="building">
            <option value="" selected>请选择居住楼号</option>
            <c:forEach items="${Buildings}" var="Building">
                <option value="${Building.BUILDINGNAME}">${Building.BUILDINGNAME}</option>
            </c:forEach>
        </select>
        <input type="text" name="region_adress" class="region_adress" placeholder="现居住单元及门牌号如：1-1101" maxlength="40">
        <label style="float: left;margin-top: 30px">返京时间:</label>
        <input type="date" name="city_name" class="city_name" placeholder="返京时间如：2020/2/3" maxlength="10" style="width: 180px;float: right">
        <input type="text" name="area_name" class="area_name" placeholder="返（来）京出发地（途径湖北注明）" maxlength="10">
        <select name="sex" class="sex">
            <option value="" selected>请选择出行方式</option>
            <option value="0">自驾</option>
            <option value="1">火车</option>
            <option value="2">飞机</option>
            <option value="3">其他</option>
        </select>
        <input type="text" name="trip_no" class="trip_no" placeholder="车次" maxlength="10">
        <select name="healthy" class="healthy" >
            <option value="" selected>是否有湖北地区人接触史</option>
            <option value="0">是</option>
            <option value="1">否</option>
        </select>
        <input type="text" name="health_note" style="font-size: 14px" class="health_note" placeholder="身体症状，如：发热、咳嗽、感冒等">
        <input type="text" name="carno" class="carno" placeholder="车牌号" maxlength="10">
        <input type="number" name="temperature" class="temperature" placeholder="请输入体温，如：36.5" min="36.0" max="43.0" step="0.1" maxlength="4" >

        <div style="text-align: left; margin-left: 10px;"></div>
<%--        <button type="button" id="Continue" style="width: 150px">继续登记</button>--%>
        <button type="button" id="submit" style="width: 100%">完成</button>
        <div class="error">
            <span>+</span>
        </div>
    </form>
</div>

</body>

</html>