<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
	integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/header.jsp" />
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment-with-locales.min.js"
			integrity="sha512-4F1cxYdMiAW98oomSLaygEwmCnIP38pb4Kx70yQYqRwLVCs3DbRumfBq82T08g/4LJ/smbFGFpmeFlQgoDccgg=="
			crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<main class="container">
			<div class="clearfix py-4">
				<h2 class="float-start">게시판 보기</h2>
				<a href="write" class="btn btn-primary float-end">글쓰기</a>
			</div>
			<div class="my-3 col-md-9 mx-auto">
				<label for="title" class="form-label mt-3"><i
					class="fa-solid fa-heading "></i> <b>제목:</b></label> <input type="text"
					class="form-control" id="title" placeholder="title" name="title"
					value="${post.title}" disabled> <label for="content"
					class="form-label mt-3"><i class="fa-solid fa-align-left "></i>
					<b>내용:</b></label>
				<textarea class="form-control" rows="20" id="content" name="content"
					disabled> ${post.content}</textarea>

				<label for="writer" class="form-label mt-3"><i
					class="fa-solid fa-user-pen"></i> <b>작성자:</b></label> <input type="text"
					class="form-control" id="writer" placeholder="writer" name="writer"
					value="${post.writer}" disabled> <label for="regdate"
					class="form-label mt-3"><i
					class="fa-regular fa-calendar-days"></i> <b>작성 날짜:</b></label> <input
					type="text" class="form-control" id="regdate" placeholder="regdate"
					name="regdate" value="${post.regdate}" disabled> <label
					for="updatedate" class="form-label mt-3"><i
					class="fa-regular fa-calendar-plus"></i><b>업데이트 날짜:</b></label> <input
					type="text" class="form-control" id="updatedate"
					placeholder="updatedate" name="updatedate"
					value="${post.updatedate}" disabled> <label
					class="form-label mt-3"><i
					class="fa-solid fa-file-arrow-up"></i><b> Attach:</b><br></label><br>


				<ul class="list-group attach-result">
					<c:if test="${empty post.attachs}">
						<li class="list-group-item">첨부파일이 없습니다.</li>
					</c:if>

					<c:forEach items="${post.attachs}" var="a">
						<li class="list-group-item"><a
							href="${cp}download?uuid=${a.uuid}&origin=${a.origin}&path=${a.path}">${a.origin}</a></li>
					</c:forEach>
				</ul>

				<!--내 댓글 구간  -->
				<div class="clearfix mt-5 mb-2">
					<label class="form-label float-start"><i
						class="fa-regular fa-comment-dots text-danger"></i><b>내 댓글:</b></label>
				</div>
				<ul class="list-group small my-replies my-2" data-bs-theme="dark">
					<li class="list-group-item" data-rno="64">
						<p class="fw-bold my-3 text-truncate">아 배고프다</p>
						<div class="clearfix">
							<span class="float-start">aa</span> <span class="float-end small">하루
								전</span> <a class="float-end  small text-danger mx-2 btn-reply-remove"
								href="#">삭제</a>
						</div>
					</li>
				</ul>

				<!-- 전체 댓글 구간 -->
				<div class="clearfix mt-5 mb-2">
						<label class="form-label float-start"><i class="fa-regular fa-comment-dots text-danger"></i><b>댓글:</b><br></label>
						<button type="button" class="btn btn-primary float-end btn-sm" id="btnWriteReply">writer reply</button>						
						
				</div>
				<ul class="list-group small replies"></ul>
				
				
				<diV class="d-grid my-3">
					<button class="btn btn-primary btn-block btn-more-reply">댓글 더보기</button>
				</diV>

				<hr>
				<div class="text-center my-5">
					<c:if test="${post.writer == member.id}">
						<a href="modify?pno=${post.pno}&${cri.qs2}"
							class="btn btn-warning">수정</a>
						<a href="remove?pno=${post.pno}&${cri.qs2}" class="btn btn-danger"
							onclick="return confirm('삭제하시겠습니까?')">삭제</a>
					</c:if>
					<a href="list?${cri.qs2}" class="btn btn-primary">목록</a>
				</div>
			</div>
		</main>

		<script src="${cp}js/reply.js"></script>
		<script>
		moment.locale("ko");

		const pno = "${post.pno}";

		// replyService.write({content : 'abcd'})
		//목록조회
		function list(cri,myOnly) {
			replyService.list(pno,cri,function(data) {
				if(!data.list.length){
					$(".btn-more-reply")
					.prop("disabled",true)
					.text("댓글이 없습니다.")
					.removeClass("btn-primary")
					.addClass("btn-dark")
					return;
				}
				
				let myStr = "";
				for(let i in data.myList){
					myStr += makeLi(data.myList[i])
				}
				$(".my-replies").html(myStr);
				// 추가 css 작업
				$(".my-replies .text-secondary, .my-replies .text-black").removeClass("text-secondary text-black");
				
				if(myOnly) return;
				
				let str = "";
				for(let i in data.list){//data의 길이로 마지막 댓글을 알아옴
					str += makeLi(data.list[i])
				}
				$(".replies").append(str);
				
				
			});
		}
		list();//리스트 함수 한번 호출
			 
			
		//단일 리스트 문자열 생성
		function makeLi(reply){
            return `<li class="list-group-item"data-rno="\${reply.rno}">
                <p class="text-black fw-bold my-3 text-truncate">\${reply.content}</p>
                <div class="clearfix text-secondary">
                    <span class="float-start">\${reply.writer}</span>
                    <span class="float-end small">\${moment(reply.regdate,'yyyy/MM/DD-HH:mm:ss').fromNow()}</span>
                    <a type="button" class="float-end  small text-danger mx-2 btn-reply-remove"href="#">삭제</a>
                </div>
            </li>`;
        }	
		//li 클릭시 이벤트
		$(".replies, .my-replies").on("click", "li", function() {

                const rno = $(this).data("rno");
                $("#replyModal").modal("show");
                replyService.view(rno, function(data) {
                    console.log(data);
                    $("#replyModal").find(".modal-footer div button").hide()
                        .filter(":gt(0)").show();

                    $("#replyModal").data("rno", rno).modal("show");
                    $("#replyContent").val(data.content);
                    $("#replyWriter").val(data.writer);
                })
            });
		//li .btn-reply-remove 클릭시 이벤트
		$(".replies, .my-replies").on("click","li .btn-reply-remove",function(){
			if(! confirm("삭제 하시겠습니까?")){
				return false;
			}
			const $li =$(this).closest("li")
			const rno =$li.data("rno");
			//console.log($(this).data("rno"))
			replyService.remove(rno,function(data){
				alert("삭제 되었습니다");
				$li.remove();
				list(undefined,true);
				
				
			});
			return false;	
			
		});
			
		// 댓글 쓰기 버튼 클릭시
        $("#btnWriteReply").click(function() {
        	
        	console.log("asdasdas");
            $("#replyModal").find(".modal-footer div button").hide()
                .filter(":eq(0)").show();

            $("#replyModal").modal("show");
            $("#replyContent").val("");
            $("#replyWriter").val("${member.id}");

        });
		//댓글 더보기 버튼 클릭시
		$(".btn-more-reply").click(function () {
			const lastRno = $(".replies li:last").data("rno");//select
			list({lastRno});//키 벨류 형태로 객체로 전달 이걸 리플라이js가 확인
		});
     
            

		$(function() {
			//댓글 작성(반영) 버튼 클릭시
              $("#btnReplyWriteSubmit").click(function() {
                    const writer = $("#replyWriter").val();
                    const content = $("#replyContent").val();
                    const reply = {pno, writer, content};
                    replyService.write(reply, function(data) {
                        $("#replyModal").modal("hide");
                        list(undefined,true);
                    });
                });
			
               /* $("#replyModal").modal("show") 들어갔을때 바로 뜨게하기 */
               
       		//댓글 수정(반영) 버튼 클릭시
       		$("#btnReplyModifySubmit").click(function() {
       			const content = $("#replyContent").val();
       			const rno = $("#replyModal").data("rno");
       			const reply = {rno, content};
                   replyService.modify(reply, function(data) {
                   	$("#replyModal").modal("hide");
                    $(`.replies li[date-rno ='\${rno}']p`).text(content);
                   	
       				
       			 	
                  })
              })
              //댓글 삭제(반영)버튼 클릭시
              $("#btnReplyRemoveSubmit").click(function() {
       			const rno = $("#replyModal").data("rno");
       			const $li = $(`.replies li[date-rno ='\${rno}']`);
       			replyService.remove(rno,function(data){
       				$("#replyModal").modal("hide");	
    				$li.remove();
    				list(undefined,true);
       				/* list();//리스트 함수 호출 */
       			
       			})
       			
              })
                   
       })
	</script>
		<jsp:include page="../common/footer.jsp" />
	</div>
	<!-- The Modal -->
	<div class="modal fade" id="replyModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Reply</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<label for="replyContent" class="mb-2">content</label> <input
						type="text" class="form-control mb-3" id="replyContent"> <label
						for="replyWriter" class="mb-2">writer</label> <input type="text"
						class="form-control mb-3" id="replyWriter" value="${member.id}">
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<div>
						<button type="button" class="btn btn-primary"
							id="btnReplyWriteSubmit">Write</button>
						<button type="button" class="btn btn-warning"
							id="btnReplyModifySubmit">Modify</button>
						<button type="button" class="btn btn-danger"
							id="btnReplyRemoveSubmit">Remove</button>
					</div>
					<button type="button" class="btn btn-dark" data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>


</body>
</html>