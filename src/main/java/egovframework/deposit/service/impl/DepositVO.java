package egovframework.deposit.service.impl;

public class DepositVO {
	
	private Long userSeq;
	private String changeReqson;
	private int beoreDeposit;
	private int afterDeposit;
	private String usageDate;
	private String status;
	
	public DepositVO(Long userSeq, String changeReqson, int beoreDeposit, int afterDeposit, String usageDate, String status) {
		this.userSeq = userSeq;
		this.changeReqson = changeReqson;
		this.beoreDeposit = beoreDeposit;
		this.afterDeposit = afterDeposit;
		this.usageDate = usageDate;
		this.status = status;
	}

	
	static public class Builder {
		
		private Long userSeq;
		private String changeReqson;
		private int beoreDeposit;
		private int afterDeposit;
		private String usageDate;
		private String status;
		
		public Builder() {}
		
		public Builder(DepositVO vo) {
			this.userSeq = vo.userSeq;
			this.changeReqson = vo.changeReqson;
			this.beoreDeposit = vo.beoreDeposit;
			this.afterDeposit = vo.afterDeposit;
			this.usageDate = vo.usageDate;
			this.status = vo.status;
		}
		
		public Builder userSeq(Long userSeq) {
			this.userSeq = userSeq;
			return this;	
		}
		
		public Builder changeReqson(String changeReqson) {
			this.changeReqson = changeReqson;
			return this;	
		}
		
		public Builder beoreDeposit(int beoreDeposit) {
			this.beoreDeposit = beoreDeposit;
			return this;	
		}
		
		public Builder afterDeposit(int afterDeposit) {
			this.afterDeposit = afterDeposit;
			return this;	
		}
		
		public Builder usageDate(String usageDate) {
			this.usageDate = usageDate;
			return this;	
		}
		
		public Builder status(String status) {
			this.status =status;
			return this;	
		}
		
		public DepositVO build() {
			return new DepositVO(userSeq, changeReqson, beoreDeposit, afterDeposit, usageDate, status);
		}
		
		
	}
}
