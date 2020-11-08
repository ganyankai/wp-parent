package cn.dante.p13build.example3;

/**
 * 描述输出到文件尾的内容的对象
 */
public class ExportFooterModel {
	/**
	 * 输出人
	 *//**
	 * 描述输出到文件头的内容的对象
	 */
	public class ExportHeaderModel {
		/**
		 * 分公司或门市点编号
		 */
		private String depId;
		/**
		 * 导出数据的日期
		 */
		private String exportDate;
		public String getDepId() {
			return depId;
		}
		public void setDepId(String depId) {
			this.depId = depId;
		}
		public String getExportDate() {
			return exportDate;
		}
		public void setExportDate(String exportDate) {
			this.exportDate = exportDate;
		}
	}

	private String exportUser;

	public String getExportUser() {
		return exportUser;
	}

	public void setExportUser(String exportUser) {
		this.exportUser = exportUser;
	}
	
}
