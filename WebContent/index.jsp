<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h2>HRD Student Management System</h2>
		</div>
	</div>

	<div class="row">

		<div class="col-md-3">
			<form class="form-inline">
				<div class="form-group">
					<input type="text" name="txt-search" id="txt-search"
						class="form-control" placeholder="Search by name ...">
				</div>
			</form>
		</div>

		<div class="col-md-3">
			<select class="form-control" name="class-name" id="class-name">
				<option value="">All Class</option>
				<option value="PP">Phnom Penh</option>
				<option value="SR">Siem Reap</option>
				<option value="BTB">Battambang</option>
				<option value="KPS">Kompong Som</option>
			</select>
		</div>

		<div class="col-md-6">
			<button class="btn btn-success pull-right" data-toggle="modal"
				data-target="#bootstrapModal" id="add-modal">Add Student</button>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12 table-responsive" id="student-info"></div>
	</div>

	<div class="modal fade" id="bootstrapModal" tabindex="-1" role="dialog"
		aria-labelledby="bootstrapModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-info">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">New Student Form</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-md-12 add">
								<form class="form-horizontal">
									
									<div class="form-group" id="validate-id">
										<label for="txt-id" class="col-sm-2 control-label">Student
											ID : </label>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="txt-id"
												tabindex=1> <span
												id="alert-id">* Please input data</span>
										</div>
									</div>
								
									<div class="form-group" id="validate-name">
										<label for="txt-name" class="col-sm-2 control-label">Student
											Name :</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="txt-name"
												tabindex=2> <span
												id="alert-name">* Please Input data</span>
										</div>
									</div>
								
									<div class="form-group">
										<label for="cbo-gender" class="col-sm-2 control-label">Gender :</label>
										<div class="col-sm-3">
											<select name="cbo-gender" id="cbo-gender"
												class="form-control" tabindex=3>
												<option value="1">Male</option>
												<option value="0">Female</option>
											</select>
										</div>
									</div>
								
									<div class="form-group">
										<label for="cbo-university" class="col-sm-2 control-label">University :</label>
										<div class="col-sm-3">
											<select name="cbo-university" id="cbo-university"
												class="form-control" tabindex=4>
												<option value="AEU">AEU</option>
												<option value="BBU">BBU</option>
												<option value="Norton">NU</option>
												<option value="NUM">NUM</option>
												<option value="RUPP">RUPP</option>
												<option value="SETEC">SETEC</option>

											</select>
										</div>
									</div>
		
									<div class="form-group">
										<label for="cbo-class" class="col-sm-2 control-label">Class :</label>
										<div class="col-sm-3">
											<select name="cbo-class" id="cbo-class" class="form-control"
												tabindex=5>
												<option value="PP">Phnom Penh</option>
												<option value="KPS">Kompong Som</option>
												<option value="SR">Siem Reap</option>
												<option value="BTB">Battambang</option>

											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="button" class="btn btn-info"
												id="add-new-student" tabindex=6>Submit</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</div>
<%@ include file="footer.jsp"%>