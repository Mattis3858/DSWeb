package com.sample.jsp.bean;
import java.io.IOException;
import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
public class Call {
	public String GoogleURL;
	public String WPaperURL;
	public String WPaperURL1 = "https://wallpapers.com/search/";
	public String GoogleURL1 = "https://www.google.com/search?q=";
	public String GoogleURL2 = "&rlz=1C5GCEA_enTW944TW944&sxsrf=AOaemvL493PTkQhbZNEJNxyKsXRVkxCixQ:1636354467688&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiLi5e2l4j0AhW9y4sBHegWArsQ_AUoAXoECAEQAw&cshid=1636354620292537&biw=1356&bih=710&dpr=1";
	public ArrayList<WebNode> URList,URList2;//��府�root
	public ArrayList<String> rand;
	public String keyword;
	public String BaseURL = "https://wallpapers.com";
	//https://www.google.com/search?q=cat&rlz=1C5GCEA_enTW944TW944&sxsrf=AOaemvL493PTkQhbZNEJNxyKsXRVkxCixQ:1636354467688&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiLi5e2l4j0AhW9y4sBHegWArsQ_AUoAXoECAEQAw&cshid=1636354620292537&biw=1356&bih=710&dpr=1
	public Call() {
		
		
	}
	public Call(String keyword) {//���摮��雯��Call嚗撓���摮銝�
		this.GoogleURL = GoogleURL1+keyword+GoogleURL2;
		this.WPaperURL = WPaperURL1+keyword;
		this.URList = new ArrayList<WebNode>();
		this.URList2 = new ArrayList<WebNode>();

		this.keyword = keyword;
		Connect1();
		createWebTree1();
		preOrder1();
		output();
		
		MaxHeap.sort(outputRoot());
	}
	
	
	public void Connect1() {
		try
		{
		    Document document = Jsoup.connect(WPaperURL).get();
		    Elements links = document.select("a[href]");  
			//Document doc = Jsoup.connect(GoogleURL).get();

		    for (Element link : links) 
		    {
		    	if(link.attr("href").contains(".html")) {//������雯��摮�RL List
		    		//System.out.println("link : " + link.attr("href"));
		    		String string = link.attr("href");
		    		WebNode webNode = new WebNode(string);
		    		URList.add(webNode);//銝���之蝝�10��
		    	}
		         //System.out.println("link : " + link.attr("href"));  
		         //System.out.println("text : " + link.text());  
		    }
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
	public void createWebTree1() {
		for(WebNode wn:URList) {
			//System.out.println(wn.getURL());
			Document document;
			try {
				document = Jsoup.connect(wn.getURL()).get();
				Elements links = document.select("a[href]"); 

			    //System.out.println(document.select("h1"));

				rand = new ArrayList<String>();
			    for (Element link : links) {
			    	if(link.attr("href").contains(".html")) {//��隡澆��雯�������椰�撠酋
			    		if(link.attr("href").indexOf("https://wall")==0) {
				    		rand.add(link.attr("href"));
			    		}
			    	}
		    		//System.out.println("link : " + link.attr("href")); 	
		    		//System.out.println("text : " + link.text()); 
			    }
				links = document.select("img");  
				for (Element link : links) {
			    	if(link.attr("data-src").contains("thumbnail")==false) {//摮���
			    		if(link.attr("data-src").contains("images")) {
			    			wn.setIMG(BaseURL+link.attr("data-src"));
				    		//System.out.println("link : " + BaseURL+link.attr("data-src"));
			    		}
			    	}
		    		//System.out.println("link : " +BaseURL+ link.attr("data-src")); 	
		    		//System.out.println("text : " + link.text()); 
			    }

			    
			    int random;
	    		random = (int)(Math.random()*rand.size());
	    		wn.setLeftChildNode(new WebNode(rand.get(random)));
	    		random = (int)(Math.random()*rand.size());
	    		wn.setRightChildNode(new WebNode(rand.get(random)));
	    		wn.setTitle(document.select("h1").toString());
	    		if(wn.getLeftChildNode().getURL().length()>10) {
	    			document = Jsoup.connect(wn.getLeftChildNode().getURL()).get();
		    		wn.getLeftChildNode().setTitle(document.select("h1").toString());
		    		links = document.select("img");  
					for (Element link : links) {
				    	if(link.attr("data-src").contains("thumbnail")==false) {//摮���
				    		if(link.attr("data-src").contains("images")) {
				    			wn.getLeftChildNode().setIMG(BaseURL+link.attr("data-src"));
					    		//System.out.println("link2 : " + BaseURL+link.attr("data-src")); 
				    		}
				    			
				    	}
					}
					try {
						wn.setScore(findLCS(wn.getTitle(), keyword));
			    		wn.getLeftChildNode().setScore(findLCS(wn.getLeftChildNode().getTitle(), keyword));
			    		wn.getRightChildNode().setScore(findLCS(wn.getRightChildNode().getTitle(), keyword));
					} catch (Exception e) {
						// TODO: handle exception
					}
	    		}else {
	    			try {
						wn.setScore(0);
			    		wn.getLeftChildNode().setScore(0);
			    		wn.getRightChildNode().setScore(0);
					} catch (Exception e) {
						// TODO: handle exception
					}
	    		}
	    		
	    		
	    		if(wn.getRightChildNode().getURL().length()>10) {
	    			document = Jsoup.connect(wn.getRightChildNode().getURL()).get();
		    		wn.getRightChildNode().setTitle(document.select("h1").toString());
		    		links = document.select("img");  
					for (Element link : links) {
				    	if(link.attr("data-src").contains("thumbnail")==false) {//摮���
				    		if(link.attr("data-src").contains("images")) {
				    			wn.getRightChildNode().setIMG(BaseURL+link.attr("data-src"));
					    		//System.out.println("link3 : " + BaseURL+link.attr("data-src")); 
				    		}
				    			

				    	}
					}
					try {
						wn.setScore(findLCS(wn.getTitle(), keyword));
			    		wn.getLeftChildNode().setScore(findLCS(wn.getLeftChildNode().getTitle(), keyword));
			    		wn.getRightChildNode().setScore(findLCS(wn.getRightChildNode().getTitle(), keyword));
					} catch (Exception e) {
						// TODO: handle exception
					}
	    		}else{
	    			try {
						wn.setScore(0);
			    		wn.getLeftChildNode().setScore(0);
			    		wn.getRightChildNode().setScore(0);
					} catch (Exception e) {
						// TODO: handle exception
					}
	    		}
	    		
	    		//��敺撖怨艘���蝚血�
				
	    		
	    		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
	public void preOrder1() {//閮剔蔭��
		for(WebNode wn:URList) {
			int temp = wn.getScore();
			try {
				wn.setScore(temp+wn.getLeftChildNode().getScore()+wn.getRightChildNode().getScore());

			} catch (Exception e) {
				// TODO: handle exception
				//not found
			}
			//�撖阡�ㄐ�if撖急���末嚗���敺������
		}
	}
	public int findLCS(String x, String y){
			int lenx = x.length();
			int leny = y.length();
			int c[][] = new int[lenx+1][leny+1];
			for (int i = 0;i<=lenx;i++) {
				for(int j = 0;j<=leny;j++) {
					if(i==0||j==0) {
						c[i][j]= 0; 
					}else if(x.charAt(i-1)==y.charAt(j-1)) {
						c[i][j] = c[i-1][j-1]+1; 
					}else {
						c[i][j]= Math.max(c[i-1][j],c[i][j-1]); 
					}
				}
			}
		    return c[lenx][leny]; 
	}
	public ArrayList<WebNode> outputRoot() {
		if(URList.size()>0) {
			return URList;
		}else {
			WebNode nullNode = new WebNode();
			nullNode.setIMG("");
			nullNode.setTitle("null");
			nullNode.setURL("");
			nullNode.setLeftChildNode(null);
			nullNode.setRightChildNode(null);
			URList.add(null);
			return URList;
		}
	}
	/*
	public void output() {
		for(WebNode wn:URList) {
			System.out.println(wn.getScore());
			System.out.println(wn.getLeftChildNode().getScore());
			System.out.println(wn.getRightChildNode().getScore());
			System.out.println("-----");

			
		}
	}*/
	public String[] getResult() {
		WebNode result = outputRoot().get(outputRoot().size()-1);
		//WebNode result2 = outputRoot().get(outputRoot().size()-2);
		//WebNode result3 = outputRoot().get(outputRoot().size()-3);

		String[] R = new String[7];
		if(outputRoot().size()>1) {
			R[0]=result.getTitle();
			R[1]=result.getURL();
			R[2]=result.getIMG();
			R[3]=result.getLeftChildNode().getTitle();
			R[4]=result.getLeftChildNode().getIMG();
			R[5]=result.getRightChildNode().getTitle();
			R[6]=result.getRightChildNode().getIMG();
		}else {
			R[0]="";
			R[1]="Not Found";
			R[2]=null;
			R[3]="Not Found";
			R[4]=null;
			R[5]="Not Found";
			R[6]=null;
		}
		
		/*
		R[7]=result2.getTitle();
		R[8]=result2.getURL();
		R[9]=result2.getIMG();
		R[10]=result3.getTitle();
		R[11]=result3.getURL();
		R[12]=result3.getIMG();
		*/
		return R;//��末��������������
		//�preorder
		//靽桀儔�銝Bug
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void Connect2() {//.html/<h1>/space
		try
		{
			Document doc = Jsoup.connect(GoogleURL).get();
		    Elements links = doc.select("img");  //print img:src,width,height,alt
		    //System.out.println(links);
		    for (Element link : links) 
		    {
		    	
			    //System.out.println(link.attr("href")+"\n"+"---");
		    	//System.out.println(link.tagName("a[href]"));
		    	if(link.attr("data-src").indexOf("https")==0) {//������雯��摮�RL List
		    		//System.out.println("src:"+link.attr("data-src"));
		    		//System.out.println("width:"+link.attr("width"));
		    		//System.out.println("height:"+link.attr("height"));
		    		//System.out.println("alt:"+link.attr("alt"));


		    		String string = link.attr("data-src");
		    		String title = link.attr("alt");
		    		String width = link.attr("width");
		    		String height = link.attr("height");
		    		if(Integer.parseInt(width)!=0&&Integer.parseInt(height)!=0) {
		    			WebNode webNode = new WebNode(string);
			    		webNode.setTitle(title);
			    		webNode.setWidth(Integer.parseInt(width));
			    		webNode.setHeight(Integer.parseInt(height));
			    		webNode.setScore(webNode.getHeight()*webNode.getWidth());
			    		URList2.add(webNode);
		    		}
		    		
		    	}
		    	
		    }
		} 
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
	/*
	public void createWebTree2() {
		for(int i=0;i<5;i++) {
			WebNode wNode = new WebNode(URList.get(i).getURL());//random?
			Document doc;
			try {
				doc = Jsoup.connect(wNode.getURL()).get();
			    Elements imgs = doc.select("img"); 
			    for(Element img:imgs) {
			    	
			    	if(img.attr("src").contains(".jpg")==true&&img.attr("src").contains("http")==false) {
				    	System.out.println("https:"+img.attr("src")+"\n"+"---");
			    	}
				    System.out.println(img+"\n"+"---");

			    	
			    }

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	public void preOrder2() {
		MaxHeap.sort(URList2);
	}
	public ArrayList<WebNode> outputRoot2() {
		return URList2;
		
	}
	public String[] getResult2() {
		WebNode result = outputRoot2().get(outputRoot2().size()-1);
		WebNode result2 = outputRoot2().get(outputRoot2().size()-2);
		WebNode result3 = outputRoot2().get(outputRoot2().size()-3);

		String[] R = new String[9];
			R[0]=result.getTitle();
			R[1]=result.getURL();
			R[2]=String.valueOf(result.getScore());
			R[3]=result2.getTitle();
			R[4]=result2.getURL();
			R[5]=String.valueOf(result2.getScore());
			R[6]=result3.getTitle();
			R[7]=result3.getURL();
			R[8]=String.valueOf(result3.getScore());
		return R;
	}
	public void constructor() {
		//this.GoogleURL = "http://www.google.com/search?q="+keyword+"&oe=utf8&num=20";
		this.GoogleURL = GoogleURL1+keyword+GoogleURL2;
		this.WPaperURL = WPaperURL1+keyword;
		this.URList = new ArrayList<WebNode>();
		this.URList2 = new ArrayList<WebNode>();

	}
	public void run1() {
		constructor();
		Connect1();
		createWebTree1();
		preOrder1();
		
		
		
		if(outputRoot().size()>2) {
			MaxHeap.sort(outputRoot());
		}
	}
	public void run2() {
		constructor();
		Connect2();
		preOrder2();
	}
	public static void main(String[] args) {
		//Call C = new Call();
		//C.setKeyword("cat");
		//C.constructor();
		//C.Connect2();
		//C.createWebTree2();
		//C.createWebTree1();
		
		//MaxHeap.sort(C.outputRoot());
        /*
		System.out.println("Heap Sort: " );
        for(WebNode wn:C.outputRoot()) {
            System.out.println(wn.getScore());
        }
		System.out.println(C.getResult().getTitle());
		System.out.println(C.getResult().getURL());
		System.out.println(C.getResult().getLeftChildNode().getTitle());
		System.out.println(C.getResult().getRightChildNode().getTitle());
*/	
	}
	
}
