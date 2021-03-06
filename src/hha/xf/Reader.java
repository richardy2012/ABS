﻿package hha.xf;


//构造方法 Reader(android.content.Context context),此处传入activity的this
//开始读 bool start(String str);成功返回true，失败返回false
//取消读 cancel(); 暂停pause();  恢复(从暂停中)resume();

import android.os.RemoteException;

import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechSynthesizer;
import com.iflytek.speech.SynthesizerListener;

public class Reader {

    //主要对象,
    private SpeechSynthesizer mTts = null;
    //用于构造mTts的对象
	private InitListener il = new InitListener(){

		@Override
		public void onInit(ISpeechModule arg0, int arg1) {
			// TODO Auto-generated method stub
			// 设置引擎类型
			mTts.setParameter(SpeechConstant.ENGINE_TYPE, "local");
			// 设置发音人
			mTts.setParameter(SpeechSynthesizer.VOICE_NAME, "xiaoyan");
			// 设置语速
			mTts.setParameter(SpeechSynthesizer.SPEED, "50");
			// 设置音调
			mTts.setParameter(SpeechSynthesizer.PITCH, "50");
		}	
	};
	
	//mTts阅读时需要使用的对象，各种重写的方法可以添加
    private SynthesizerListener mTtsListener = new SynthesizerListener.Stub() {
        @Override
        public void onBufferProgress(int progress) throws RemoteException {

        }

        @Override
        public void onCompleted(int code) throws RemoteException {

        }

        @Override
        public void onSpeakBegin() throws RemoteException {

        }

        @Override
        public void onSpeakPaused() throws RemoteException {

        }

        @Override
        public void onSpeakProgress(int progress) throws RemoteException {

        }

        @Override
        public void onSpeakResumed() throws RemoteException {
        	
        }
    };
    
    //构造方法
    public Reader(android.content.Context context){
    	mTts = new SpeechSynthesizer(context,il);
    }
    
    //开始合成
    public boolean start(String str){
    	int code = mTts.startSpeaking(str, mTtsListener);
    	if(code==0) return true;
    	else return false;
    }
    
    //取消合成
    public void cancel(){
    	mTts.stopSpeaking(mTtsListener);
    }
    
    //暂停合成
    public void pause(){
    	mTts.pauseSpeaking(mTtsListener);
    }
    
    //继续合成
    public void resume(){
    	mTts.resumeSpeaking(mTtsListener);
    }
}
