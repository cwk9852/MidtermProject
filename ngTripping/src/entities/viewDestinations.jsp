<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="./assets/img//apple-icon.png">
<link rel="icon" type="image/png" href="./assets/img//favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>tripping</title>
<link
	href="https://fonts.googleapis.com/css?family=Ranchers&display=swap"
	rel="stylesheet">
<jsp:include page="../bootstrapHead.jsp" />
</head>
<body class="index-page sidebar-collapse"
	style="background-color: #B0E0E6;">
	<jsp:include page="../navbar.jsp" />
	<div class="main-section">
		<div class="moving-clouds"
			style="background-image: url('./assets/img/clouds.png');"></div>
		<h6 class="category category-absolute"></h6>
		<c:forEach var="destination" items="${destinations}">
			<div class="entity-container card" style="height: 232px;">
				<%-- <a href="viewDestination.do?id=${destination.id }"> --%>
				<div class="section text-center entity-card-image"
					style="background-image: url('${destination.image}'); height: 230px;">
				</div>
				<!-- </a> -->
				<div class="entity-content dest"
					style="z-index: 10; margin-top: -181px;">
					<a href="viewDestination.do?id=${destination.id }">
						<h6 class="entity-title"
							style="font-family: 'Ranchers', cursive; color: orange; font-size: 36px; text-shadow: 1px 1px green;">${destination.name }</h6>
					</a>
					<%-- <c:choose>
							<c:when test="${! empty sessionScope.user}">
								<a href="#review" class="navbar-brand" rel="tooltip"
									title="login" data-placement="bottom" data-toggle="modal"
									data-target="#tripModal" style="
								    width: 95%;
								    color: orange;
								    text-align: center;
								    font-weight: 400;"> Add To Trip Journal </a>
					    	</c:when>
					    	<c:otherwise>
					    		
					    	</c:otherwise>
					    </c:choose> --%>

					<%-- <a href="viewDestination.do?id=${destination.id }">$</a> --%>
					<%-- <br> <a href="createDestination.do">Create Destination</a><br>

					<br> <a
						href="createPointComment.do?id=${point.id }">FIX Create Point
						Review</a><br> <a href="createEventReview.do?id=${event.id }">FIX
						Create Event Review</a><br>
						href="updateDestination.do?id=${destination.id }">FIX ME
						Update Destination</a><br> <a
						href="updateDestination.do?id=${destination.id }">FIX ME
						Update Point Add/Remove Activity</a><br> <a
						href="updateDestination.do?id=${destination.id }">FIX ME Add
						Point of Interest</a><br> <a
						href="removeDestination.do?id=${destination.id }">Remove
						Destination</a><br> <a
						href="removePointOfInterestComment.do?id=${destination.id }">Remove
						Point of Interest</a><br> <a
						href="removeDestinationReview.do?id=${destination.id }">Remove
						Destination Review</a><br> <a
						href="removePointOfInterestComment.do?id=${destination.id }">Remove
						Point of Interest Comment</a><br> <a
						href="removeEventReview.do?id=${destination.id }">Remove Event
						Review</a><br> <a
						href="removeEventReview.do?id=${destination.id }">Add/Remove
						Activity/Amenity</a><br> --%>
					<%-- <c:forEach var="p" items="${destination.getPoints()}">
						<h6 style="width: 95%; text-align: center; color: orange;"><a href="viewPoint.do?id=${p.id }" style="color: orange;">${p.name }</a></h6>
					</c:forEach> --%>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="modal fade" id="tripModal" tabindex="-1" role="dialog"
		aria-hidden="false">
		<div class="modal-dialog modal-register">
			<div class="modal-content">
				<div class="modal-header no-border-header text-center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<form:form class="container text-center" action="addTrip.do"
						modelAttribute="journalEntry">
						<h3>Add Trip to Journal</h3>
						<div>
							<div class="row">

								<div class="col-md-7 col-sm-7">
									<div class="form-group">
										<h6>New Trip</h6>
										<h6>
											Trip Name
											<!-- <span class="icon-danger">*</span> -->
										</h6>
										<form:input type="text" class="form-control border-input"
											placeholder="Trip to ${point.name }" name="title"
											path="title" />
									</div>
									<div class="form-group">
										<h6>Notes</h6>
										<form:textarea class="form-control textarea-limited"
											placeholder="" rows="13" maxlength="4500" path="entryText"></form:textarea>

									</div>
								</div>
							</div>
							<div class="row buttons-row">
								<div class="col-md-4 col-sm-4">
									<button class="btn btn-outline-danger btn-block btn-round"
										type="reset">Cancel</button>
								</div>
								<div class="col-md-4 col-sm-4">
									<button class="btn btn-outline-primary btn-block btn-round"
										type="submit">Save</button>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- addComment modal -->
	<div class="modal fade" id="addCommentModal" tabindex="-1"
		role="dialog" aria-hidden="false">
		<div class="modal-dialog modal-register">
			<div class="modal-content">
				<div class="modal-header no-border-header text-center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<form class="container" action="addComment.do">
						<h3>New Comment ${point.name }</h3>
						<div>
							<div class="row">
								<div class="col-md-7 col-sm-7">
									<div class="form-group">
										<h6>Comment Text</h6>
										<textarea class="form-control textarea-limited" placeholder=""
											rows="13" maxlength="300"></textarea>
										<h5>
											<small> <span id="textarea-limited-message"
												class="pull-right">300 characters left</span>
											</small>
										</h5>
									</div>
								</div>
							</div>
							<div class="row buttons-row">
								<div class="col-md-4 col-sm-4">
									<button class="btn btn-outline-danger btn-block btn-round"
										type="reset">Cancel</button>
								</div>
								<div class="col-md-4 col-sm-4">
									<button class="btn btn-outline-primary btn-block btn-round"
										type="submit">Save</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../modals.jsp" />
	<jsp:include page="../bootstrapFoot.jsp" />
</body>
</html>