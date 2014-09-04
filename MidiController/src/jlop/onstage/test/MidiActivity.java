package jlop.onstage.test;

import jp.kshoji.driver.midi.activity.AbstractSingleMidiActivity;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;
import android.app.Activity;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//public class MainActivity extends Activity {
public class MidiActivity extends AbstractSingleMidiActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	String providedProgramNumber = extras.getString("ProgramNumber");
        	EditText editMidiProgram = (EditText) findViewById(R.id.edit_midi_progam);
    		if (editMidiProgram != null && editMidiProgram.getText() != null) {
    			
    			editMidiProgram.setText(providedProgramNumber);
    		}
        }
        finish();
    }

    public void onSendProgramChangeClick(View view) {
		EditText editMidiProgram = (EditText) findViewById(R.id.edit_midi_progam);
		if (editMidiProgram != null && editMidiProgram.getText() != null) {
			try {
				int program = Integer.parseInt(editMidiProgram.getText().toString());
				if (program < 0 || program > 127) throw new NumberFormatException();
				sendMidiProgramChange(program);
			}
			catch(NumberFormatException nfe) {
				Toast.makeText(this, "Provide number between 0 and 127.", Toast.LENGTH_LONG).show();
			}
		}
	}
    
    public void onSendControlChangeClick(View view) {
		EditText editTextMidiController = (EditText) findViewById(R.id.edit_midi_controller);
		EditText editTextControllerValue = (EditText) findViewById(R.id.edit_controller_value);
		if (editTextMidiController != null && editTextMidiController.getText() != null) {
			try {
				int controller = Integer.parseInt(editTextMidiController.getText().toString());
				if (controller < 0 || controller > 127) throw new NumberFormatException();
				if (editTextControllerValue != null && editTextControllerValue.getText() != null) {
					int controllerValue = Integer.parseInt(editTextControllerValue.getText().toString());
					if (controllerValue < 0 || controllerValue > 127) throw new NumberFormatException();
					sendMidiControlChange(controller, controllerValue);
				}
			}
			catch(NumberFormatException nfe) {
				Toast.makeText(this, "Provide number between 0 and 127.", Toast.LENGTH_LONG).show();
			}
		}
	}
    
    private int nextTempoControllerValue = 0;
    public void onTapTempoClick(View view) {
    	sendMidiControlChange(80, nextTempoControllerValue);
    	if (nextTempoControllerValue == 0) {
    		nextTempoControllerValue = 127;
    	} else {
    		nextTempoControllerValue = 0;
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
		Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been detached.", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
		Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been attached.", Toast.LENGTH_LONG).show();	
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
			int channel, int controller, int value) {
		Toast.makeText(this, "USB MIDI Device " + sender.getUsbDevice().getDeviceName() + 
				" - ControlChange cable: " + cable + ", channel: " + channel + ", conroller: " + controller + ", value: " + value, Toast.LENGTH_LONG).show();	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable, int channel, int program) {
		Toast.makeText(this, "USB MIDI Device " + sender.getUsbDevice().getDeviceName() + 
				" - ProgramChange cable: " + cable + ", channel: " + channel + ", program: " + program, Toast.LENGTH_LONG).show();
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
