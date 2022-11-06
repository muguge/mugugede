<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_rescue_station') || $check_field('add','name_of_rescue_station') || $check_field('set','name_of_rescue_station')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="救助站名称" prop="name_of_rescue_station">
					<el-input id="name_of_rescue_station" v-model="form['name_of_rescue_station']" placeholder="请输入救助站名称"
							  v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','name_of_rescue_station')) || (!form['rescue_station_information_id'] && $check_field('add','name_of_rescue_station'))" :disabled="disabledObj['name_of_rescue_station_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_rescue_station')">{{form['name_of_rescue_station']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','capacity_of_rescue_station') || $check_field('add','capacity_of_rescue_station') || $check_field('set','capacity_of_rescue_station')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="救助站容量" prop="capacity_of_rescue_station">
					<el-input id="capacity_of_rescue_station" v-model="form['capacity_of_rescue_station']" placeholder="请输入救助站容量"
							  v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','capacity_of_rescue_station')) || (!form['rescue_station_information_id'] && $check_field('add','capacity_of_rescue_station'))" :disabled="disabledObj['capacity_of_rescue_station_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','capacity_of_rescue_station')">{{form['capacity_of_rescue_station']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','address_of_rescue_station') || $check_field('add','address_of_rescue_station') || $check_field('set','address_of_rescue_station')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="救助站地址" prop="address_of_rescue_station">
					<el-input id="address_of_rescue_station" v-model="form['address_of_rescue_station']" placeholder="请输入救助站地址"
							  v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','address_of_rescue_station')) || (!form['rescue_station_information_id'] && $check_field('add','address_of_rescue_station'))" :disabled="disabledObj['address_of_rescue_station_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','address_of_rescue_station')">{{form['address_of_rescue_station']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','person_in_charge') || $check_field('add','person_in_charge') || $check_field('set','person_in_charge')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="负责人" prop="person_in_charge">
					<el-input id="person_in_charge" v-model="form['person_in_charge']" placeholder="请输入负责人"
							  v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','person_in_charge')) || (!form['rescue_station_information_id'] && $check_field('add','person_in_charge'))" :disabled="disabledObj['person_in_charge_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','person_in_charge')">{{form['person_in_charge']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系方式" prop="contact_information">
					<el-input id="contact_information" v-model="form['contact_information']" placeholder="请输入联系方式"
							  v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','contact_information')) || (!form['rescue_station_information_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_information')">{{form['contact_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','rescue_station_picture') || $check_field('add','rescue_station_picture') || $check_field('set','rescue_station_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="救助站图片" prop="rescue_station_picture">
					<el-upload :disabled="disabledObj['rescue_station_picture_isDisabled']" id="rescue_station_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_rescue_station_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['rescue_station_information_id'] && $check_field('set','rescue_station_picture')) || (!form['rescue_station_information_id'] && $check_field('add','rescue_station_picture'))">
						<img v-if="form['rescue_station_picture']" :src="$fullUrl(form['rescue_station_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','rescue_station_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['rescue_station_picture'])" :preview-src-list="[$fullUrl(form['rescue_station_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "rescue_station_information_id",
				url_add: "~/api/rescue_station_information/add?",
				url_set: "~/api/rescue_station_information/set?",
				url_get_obj: "~/api/rescue_station_information/get_obj?",
				url_upload: "~/api/rescue_station_information/upload?",

				query: {
					"rescue_station_information_id": 0,
				},

				form: {
					"name_of_rescue_station":'', // 救助站名称
					"capacity_of_rescue_station":'', // 救助站容量
					"address_of_rescue_station":'', // 救助站地址
					"person_in_charge":'', // 负责人
					"contact_information":'', // 联系方式
					"rescue_station_picture":'', // 救助站图片
					"rescue_station_information_id": 0, // ID

				},
				disabledObj:{
					"name_of_rescue_station_isDisabled": false,
					"capacity_of_rescue_station_isDisabled": false,
					"address_of_rescue_station_isDisabled": false,
					"person_in_charge_isDisabled": false,
					"contact_information_isDisabled": false,
					"rescue_station_picture_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传救助站图片
			 * @param {Object} param图片参数
			 */
			upload_rescue_station_picture(param){
				this.uploadFile(param.file, "rescue_station_picture");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/rescue_station_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/rescue_station_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/rescue_station_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/rescue_station_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/rescue_station_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
