# MessageView

说明：
    
    本View是基于BadgeView作者的思想实现的！保证了View在小屏幕手机上也不会变形！
    
    
Thanks：
    
      BadgeView作者！
      
    
 Usage：
    
      MessageView messageView = new MessageView(this);
        messageView.setMessageQuantity(3); // 设置消息数量
        messageView.setMessageTextSize(15); // 设置显示文本字体大小
        messageView.setTopMargin(5); //
        messageView.setLeftMargin(78); //
        messageView.setMessageTextColor(Color.LTGRAY); // 设置显示文本字体颜色
        messageView.setMessageBackgroundColor(Color.DKGRAY); // 设置消息背景颜色
        messageView.setDependView(findViewById(R.id.btn_button)); // 设置依附目标
