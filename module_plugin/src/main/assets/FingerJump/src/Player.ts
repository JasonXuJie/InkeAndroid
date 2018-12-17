class Player extends egret.Sprite {
	public constructor() {
		super();
		this.getPlayerAnimation();

	}

	private playerWalkAnimation: egret.MovieClip;

	private getPlayerAnimation() {
		let mcDataFactory: egret.MovieClipDataFactory
			= new egret.MovieClipDataFactory(RES.getRes("robotJump_json"), RES.getRes("robotJump_png"));
		let mc: egret.MovieClip = new egret.MovieClip(mcDataFactory.generateMovieClipData("robotJump"));

		mc.anchorOffsetX = mc.width / 2;
		mc.anchorOffsetY = mc.height;

		this.playerWalkAnimation = mc;
		this.addChild(this.playerWalkAnimation);

	}

	//0:往左跳，1：往右跳
	public playerJump(direction: number) {
		let playerJump: egret.MovieClip = this.playerWalkAnimation;
		let lastDirection: number;
		if (lastDirection == direction) {
			playerJump.play(2);
		} else {
			if (direction == 0) {
				playerJump.scaleX = 1;
			} else {
				playerJump.scaleX = -1;
			}
			playerJump.play(2);
		}
		lastDirection = direction;
	}

	//0:面向左，1：面向右
	public playerDirection(direction: number){
		if(direction == 0){
			this.playerWalkAnimation.scaleX = 1;
		}
		if(direction == 1){
			this.playerWalkAnimation.scaleX = -1;
		}
	}

	// 0:跳错 1:时间到
	public playerDied(diedNum:number){
		if(diedNum == 0){
			this.playerWalkAnimation.addEventListener(egret.Event.COMPLETE,this.playOver,this);
		}
		if(diedNum == 1){
			this.playOver();
		}

	}

	private playOver(){
		this.playerWalkAnimation.removeEventListener(egret.Event.COMPLETE,this.playOver,this);
		egret.Tween.get(this).to({y:this.y + 200},350).call(function(){
				this.parent.removeChild(this);});
	}

}