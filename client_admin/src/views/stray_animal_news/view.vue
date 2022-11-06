<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','animal_name') || $check_field('add','animal_name') || $check_field('set','animal_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="动物名称" prop="animal_name">
					<el-input id="animal_name" v-model="form['animal_name']" placeholder="请输入动物名称"
							  v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','animal_name')) || (!form['stray_animal_news_id'] && $check_field('add','animal_name'))" :disabled="disabledObj['animal_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','animal_name')">{{form['animal_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','animal_pictures') || $check_field('add','animal_pictures') || $check_field('set','animal_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="动物图片" prop="animal_pictures">
					<el-upload :disabled="disabledObj['animal_pictures_isDisabled']" id="animal_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_animal_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','animal_pictures')) || (!form['stray_animal_news_id'] && $check_field('add','animal_pictures'))">
						<img v-if="form['animal_pictures']" :src="$fullUrl(form['animal_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','animal_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['animal_pictures'])" :preview-src-list="[$fullUrl(form['animal_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adoption_status') || $check_field('add','adoption_status') || $check_field('set','adoption_status')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="领养状态" prop="adoption_status">
					<el-select id="adoption_status" v-model="form['adoption_status']"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','adoption_status')) || (!form['stray_animal_news_id'] && $check_field('add','adoption_status'))">
						<el-option v-for="o in list_adoption_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','adoption_status')">{{form['adoption_status']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','animal_sex') || $check_field('add','animal_sex') || $check_field('set','animal_sex')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="动物性别" prop="animal_sex">
					<el-select id="animal_sex" v-model="form['animal_sex']"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','animal_sex')) || (!form['stray_animal_news_id'] && $check_field('add','animal_sex'))">
						<el-option v-for="o in list_animal_sex" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','animal_sex')">{{form['animal_sex']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age_animal') || $check_field('add','age_animal') || $check_field('set','age_animal')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="动物年龄" prop="age_animal">
					<el-input id="age_animal" v-model="form['age_animal']" placeholder="请输入动物年龄"
							  v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','age_animal')) || (!form['stray_animal_news_id'] && $check_field('add','age_animal'))" :disabled="disabledObj['age_animal_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age_animal')">{{form['age_animal']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','affiliated_rescue_station') || $check_field('add','affiliated_rescue_station') || $check_field('set','affiliated_rescue_station')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所属救助站" prop="affiliated_rescue_station">
					<el-input id="affiliated_rescue_station" v-model="form['affiliated_rescue_station']" placeholder="请输入所属救助站"
							  v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','affiliated_rescue_station')) || (!form['stray_animal_news_id'] && $check_field('add','affiliated_rescue_station'))" :disabled="disabledObj['affiliated_rescue_station_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','affiliated_rescue_station')">{{form['affiliated_rescue_station']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','species') || $check_field('add','species') || $check_field('set','species')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物种" prop="species">
					<el-input id="species" v-model="form['species']" placeholder="请输入物种"
							  v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','species')) || (!form['stray_animal_news_id'] && $check_field('add','species'))" :disabled="disabledObj['species_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','species')">{{form['species']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','varieties') || $check_field('add','varieties') || $check_field('set','varieties')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="品种" prop="varieties">
					<el-input id="varieties" v-model="form['varieties']" placeholder="请输入品种"
							  v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','varieties')) || (!form['stray_animal_news_id'] && $check_field('add','varieties'))" :disabled="disabledObj['varieties_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','varieties')">{{form['varieties']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccination') || $check_field('add','vaccination') || $check_field('set','vaccination')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="是否接种" prop="vaccination">
					<el-select id="vaccination" v-model="form['vaccination']"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','vaccination')) || (!form['stray_animal_news_id'] && $check_field('add','vaccination'))">
						<el-option v-for="o in list_vaccination" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','vaccination')">{{form['vaccination']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','sterilization') || $check_field('add','sterilization') || $check_field('set','sterilization')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="是否绝育" prop="sterilization">
					<el-select id="sterilization" v-model="form['sterilization']"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','sterilization')) || (!form['stray_animal_news_id'] && $check_field('add','sterilization'))">
						<el-option v-for="o in list_sterilization" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','sterilization')">{{form['sterilization']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','is_it_insect_repellent') || $check_field('add','is_it_insect_repellent') || $check_field('set','is_it_insect_repellent')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="是否驱虫" prop="is_it_insect_repellent">
					<el-select id="is_it_insect_repellent" v-model="form['is_it_insect_repellent']"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','is_it_insect_repellent')) || (!form['stray_animal_news_id'] && $check_field('add','is_it_insect_repellent'))">
						<el-option v-for="o in list_is_it_insect_repellent" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','is_it_insect_repellent')">{{form['is_it_insect_repellent']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adoption_notes') || $check_field('add','adoption_notes') || $check_field('set','adoption_notes')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="领养备注" prop="adoption_notes">
					<el-input type="textarea" id="adoption_notes" v-model="form['adoption_notes']" placeholder="请输入领养备注"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','adoption_notes')) || (!form['stray_animal_news_id'] && $check_field('add','adoption_notes'))" :disabled="disabledObj['adoption_notes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adoption_notes')">{{form['adoption_notes']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','detailed_information') || $check_field('add','detailed_information') || $check_field('set','detailed_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="详细信息" prop="detailed_information">
					<el-input type="textarea" id="detailed_information" v-model="form['detailed_information']" placeholder="请输入详细信息"
						v-if="user_group === '管理员' || (form['stray_animal_news_id'] && $check_field('set','detailed_information')) || (!form['stray_animal_news_id'] && $check_field('add','detailed_information'))" :disabled="disabledObj['detailed_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','detailed_information')">{{form['detailed_information']}}</div>
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
				field: "stray_animal_news_id",
				url_add: "~/api/stray_animal_news/add?",
				url_set: "~/api/stray_animal_news/set?",
				url_get_obj: "~/api/stray_animal_news/get_obj?",
				url_upload: "~/api/stray_animal_news/upload?",

				query: {
					"stray_animal_news_id": 0,
				},

				form: {
					"animal_name":'', // 动物名称
					"animal_pictures":'', // 动物图片
					"adoption_status":'', // 领养状态
					"animal_sex":'', // 动物性别
					"age_animal":'', // 动物年龄
					"affiliated_rescue_station":'', // 所属救助站
					"species":'', // 物种
					"varieties":'', // 品种
					"vaccination":'', // 是否接种
					"sterilization":'', // 是否绝育
					"is_it_insect_repellent":'', // 是否驱虫
					"adoption_notes":'', // 领养备注
					"detailed_information":'', // 详细信息
					"stray_animal_news_id": 0, // ID

				},
				disabledObj:{
					"animal_name_isDisabled": false,
					"animal_pictures_isDisabled": false,
					"adoption_status_isDisabled": false,
					"animal_sex_isDisabled": false,
					"age_animal_isDisabled": false,
					"affiliated_rescue_station_isDisabled": false,
					"species_isDisabled": false,
					"varieties_isDisabled": false,
					"vaccination_isDisabled": false,
					"sterilization_isDisabled": false,
					"is_it_insect_repellent_isDisabled": false,
					"adoption_notes_isDisabled": false,
					"detailed_information_isDisabled": false,
				},
				//领养状态选项列表
				list_adoption_status: ['已领养','未领养','待审核领养'],
				//动物性别选项列表
				list_animal_sex: ['雌','雄'],
				//是否接种选项列表
				list_vaccination: ['是','否'],
				//是否绝育选项列表
				list_sterilization: ['是','否'],
				//是否驱虫选项列表
				list_is_it_insect_repellent: ['是','否'],

			}
		},
		methods: {
			/**
			 * 上传动物图片
			 * @param {Object} param图片参数
			 */
			upload_animal_pictures(param){
				this.uploadFile(param.file, "animal_pictures");
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
					bl = this.$check_action('/stray_animal_news/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/stray_animal_news/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/stray_animal_news/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/stray_animal_news/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/stray_animal_news/view','get');
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
