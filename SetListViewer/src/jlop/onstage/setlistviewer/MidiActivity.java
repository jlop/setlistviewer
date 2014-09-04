package jlop.onstage.setlistviewer;

import jp.kshoji.driver.midi.activity.AbstractSingleMidiActivity;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MidiActivity extends AbstractSingleMidiActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_midi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	String midiCommand = extras.getString("MidiCommand");
        	if (midiCommand == "ProgramChange") {
        		int programNumber = Integer.parseInt(extras.getString("ProgramNumber"));
        		sendMidiProgramChange(programNumber);
        	} else if (midiCommand == "ControlChange") {
        		int controllerNumber = Integer.parseInt(extras.getString("ControllerNumber"));
        		int controllerValue = Integer.parseInt(extras.getString("ControllerValue"));
        		sendMidiControlChange(controllerNumber, controllerValue);
        	}
        }
        
        finish();
	}

	private int nextTempoControllerValue = 0;
    public boolean sendTempoTap(View view) {
    	boolean success = sendMidiControlChange(80, nextTempoControllerValue);
    	if (success) {
	    	if (nextTempoControllerValue == 0) {
	    		nextTempoControllerValue = 127;
	    	} else {
	    		nextTempoControllerValue = 0;
	    	}
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private boolean sendMidiProgramChange(int program) {
    	return sendMidiProgramChange(getMidiOutputDevice(), 0, 0, program);
    }
    
    private boolean sendMidiProgramChange(MidiOutputDevice receiver, int cable, int channel, int program) {
    	if (receiver != null) {
    		receiver.sendMidiProgramChange(cable, channel, program);
    		Toast.makeText(this, "Program " + program + " sent to " + receiver.getUsbDevice().getDeviceName() + ".", Toast.LENGTH_LONG).show();
    		return true;
    	} else {
			Toast.makeText(this, "No MIDI device connected.", Toast.LENGTH_LONG).show();
    		return false;
    	}
    }
    
    private boolean sendMidiControlChange(int controller, int value) {
    	return sendMidiControlChange(getMidiOutputDevice(), 0, 0, controller, value);
    }
    
    private boolean sendMidiControlChange(MidiOutputDevice receiver, int cable, int channel, int controller, int value) {
    	if (receiver != null) {
    		receiver.sendMidiControlChange(cable, channel, controller, value);
    		Toast.makeText(this, "Control change " + controller + "/" + value + " sent to " + receiver.getUsbDevice().getDeviceName() + ".", Toast.LENGTH_LONG).show();
    		return true;
    	} else {
			Toast.makeText(this, "No MIDI device connected.", Toast.LENGTH_LONG).show();
    		return false;
    	}
    }
    
	@Override
	public void onDeviceDetached(UsbDevice usbDevice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice sender,
			int cable, int byte1, int byte2, int byte3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiCableEvents(MidiInputDevice sender, int cable, int byte1,
			int byte2, int byte3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice sender, int cable,
			byte[] bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemExclusive(MidiInputDevice sender, int cable,
			byte[] systemExclusive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOff(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOn(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice sender, int cable,
			int channel, int note, int pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiControlChange(MidiInputDevice sender, int cable,
			int channel, int function, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable,
			int channel, int program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiChannelAftertouch(MidiInputDevice sender, int cable,
			int channel, int pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPitchWheel(MidiInputDevice sender, int cable,
			int channel, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSingleByte(MidiInputDevice sender, int cable, int byte1) {
		// TODO Auto-generated method stub
		
	}
}
