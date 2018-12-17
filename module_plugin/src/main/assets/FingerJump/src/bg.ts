class bg extends egret.DisplayObjectContainer{
	public constructor() {
		super();
		this.addEventListener(egret.Event.ADDED_TO_STAGE,this.onAddToStage,this);
	}

	private stageW:number;
	private stageH:number;

	private onAddToStage(){

		this.removeEventListener(egret.Event.ADDED_TO_STAGE,this.onAddToStage,this);
		
		this.stageW = this.stage.stageWidth;
		this.stageH = this.stage.stageHeight;

		let bg:egret.Shape = new egret.Shape();
		bg.graphics.beginFill(0x001605);
		bg.graphics.drawRect(0,0,this.stageW,this.stageH);
		bg.graphics.endFill();

		this.addChild(bg);
	}
}