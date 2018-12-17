class leaf extends egret.Sprite {
	public constructor() {
		super();
		this.addEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);
	}

	private stageW: number;
	private stageH: number;
	private leftLeaf1: egret.Bitmap;
	private leftLeaf2: egret.Bitmap;
	private rightLeaf1: egret.Bitmap;
	private rightLeaf2: egret.Bitmap;

	private onAddToStage() {

		this.removeEventListener(egret.Event.ADDED_TO_STAGE, this.onAddToStage, this);

		this.stageW = this.stage.stageWidth;
		this.stageH = this.stage.stageHeight;

		this.leftLeaf1 = new egret.Bitmap(RES.getRes("leaf_left_png"));
		this.leftLeaf2 = new egret.Bitmap(RES.getRes("leaf_left_png"));
		this.rightLeaf1 = new egret.Bitmap(RES.getRes("leaf_right_png")); 
		this.rightLeaf2 = new egret.Bitmap(RES.getRes("leaf_right_png")); 
		
		this.addChild(this.leftLeaf1);
		this.leftLeaf1.x = 0;
		this.leftLeaf1.y = this.stageH - this.leftLeaf1.height;

		this.addChild(this.leftLeaf2);
		this.leftLeaf2.x = 0;
		this.leftLeaf2.y = this.leftLeaf1.y - this.leftLeaf2.height;

		this.addChild(this.rightLeaf1);
		this.rightLeaf1.x = this.stageW - this.rightLeaf1.width;
		this.rightLeaf1.y = this.stageH - this.rightLeaf1.height;

		this.addChild(this.rightLeaf2);
		this.rightLeaf2.x = this.stageW - this.rightLeaf2.width;
		this.rightLeaf2.y = this.rightLeaf1.y - this.rightLeaf2.height;

	}

	public move(){
		this.leafMove(this.leftLeaf1);
		this.leafMove(this.leftLeaf2);
		this.leafMove(this.rightLeaf1);
		this.leafMove(this.rightLeaf2);
	}

	private leafMove(leaf:egret.Bitmap){
		egret.Tween.get(leaf).to({y:leaf.y+50},200).call(this.leafMoveOver,this,[leaf]);
		
	}

	private leafMoveOver(leaf){
		egret.Tween.removeTweens(leaf);
		if(leaf.y>=this.stageH){
			leaf.y -= leaf.height*2;	
		}
	}
}