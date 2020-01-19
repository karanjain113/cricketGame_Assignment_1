package com.example.springboot;

import java.util.Random;

public class Match {
    public enum Result{WIN,DRAW,LOSE,NA};
    Result r;
    Team A;
    Team B;
    String result="";
    Integer[] wicketsInnings=new Integer[2];
    Integer[] runsInnings=new Integer[2];
//    Integer target;
    Integer overs;
    Team tossWon;
    Integer[] balls=new Integer[2];
    Match()
    {
        this.A=new Team("RandomTeam1");
        this.B=new Team("RandomTeam2");
        wicketsInnings[0]=0;
        wicketsInnings[1]=0;
//        target=0;
        runsInnings[0]=0;
        runsInnings[1]=0;
        overs=50;
    }
    Match(Team A,Team B,int overs)
    {
        this.A=A;
        this.B=B;
        wicketsInnings[0]=0;
        wicketsInnings[1]=0;
//        target=0;
        runsInnings[0]=0;
        runsInnings[1]=0;
        this.overs=overs;
    }
    public String startMatch()
    {
        toss();
        decision();
        int i;
        for(i=1;i<=(6*overs);i++)
        {
            Random rand=new Random();
            int next=rand.nextInt(8);
            if(next==7)
            {
                wicketsInnings[0]++;
            }
            else{
                runsInnings[0]+=next;
            }
            if(wicketsInnings[0]==10)
            {
                break;
            }
        }
        balls[0]=i;
//        target=runsInnings[0]+1;
        for(i=1;i<=(6*overs);i++)
        {
            Random rand=new Random();
            int next=rand.nextInt(8);
            if(next==7)
            {
                wicketsInnings[1]++;
            }
            else{
                runsInnings[1]+=next;
            }
            if(wicketsInnings[1]==10 || runsInnings[1]>runsInnings[0])
            {
                break;
            }
        }
        balls[1]=i;
        if(runsInnings[1]>runsInnings[0])
        {
            r= Result.WIN;
        }
        else if(runsInnings[1]==runsInnings[0])
        {
            r=Result.DRAW;
        }
        else{
            r=Result.LOSE;
        }
        return result();
    }
    void toss()
    {
        if(Math.random()<0.5)
        {
            result = result + A.name +" won the toss and ";
            tossWon=A;
        }
        else{
            result = result + B.name +" won the toss and ";
            tossWon=B;
        }
    }
    void decision()
    {
        if(Math.random()<0.5)
        {
            result = result+"elected to bat first.<br><br> ";
            if(tossWon==B)
            {
                Team temp=B;
                B=A;
                A=temp;
            }
        }
        else{
            result = result+"elected to ball first. <br><br>";
            if(tossWon==A)
            {
                Team temp=B;
                B=A;
                A=temp;
            }
        }
    }
    public String result()
    {
        if(r==Result.NA)
        {
            result=result+"Match Abandoned";
            return result;
        }
        result=result+A.name+"  "+Integer.toString(runsInnings[0])+"/"+Integer.toString(wicketsInnings[0])+" in ";

        if(balls[0]%6==0)
        {
            result=result+Integer.toString(balls[0]/6)+" overs.<br>";
        }
        else{
            result=result+Integer.toString(balls[0]/6)+"."+Integer.toString(balls[0]%6)+ " overs.<br>";
        }
        result=result+B.name+"  "+Integer.toString(runsInnings[1])+"/"+Integer.toString(wicketsInnings[1])+" in ";
        if(balls[1]%6==0)
        {
            result=result+Integer.toString(balls[1]/6)+" overs.  <br> <br>";
        }
        else{
            result=result+Integer.toString(balls[1]/6)+"."+Integer.toString(balls[1]%6)+ " overs. <br ><br>";
        }
            if(r==Result.WIN)
            {
                result=result+B.name+" Won the match by "+Integer.toString(10-wicketsInnings[1])+" wickets";
            }
            else if(r==Result.DRAW)
            {
                result=result+"Match was Draw";
            }
            else
            {
                result=result+A.name+" Won the match by "+Integer.toString(runsInnings[0]-runsInnings[1])+" runs";
            }
            result=result+"<br><br> <button onclick=\"window.location.href='result'\">Another Match</button>";
            return result;
    }
}
