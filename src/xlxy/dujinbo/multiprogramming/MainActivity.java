package xlxy.dujinbo.multiprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.media.tv.TvContract.Programs;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Button addProgramBt;
	private TextView showMsgTv;
	private TextView showAddPro;
	private String addPro = "";
	private String runPro = "";
	private String ID = "ID";
	private String NAME = "NAME";
	private String CPU_TIME = "CPU_TIME";
	private String IO_TIME = "IO_TIME";
	private int id = 0;
	private int cpu_time = 10;
	private int io_time = 20;

	// ID, Name, CPU_Time, I/O_Time
	private Map<String, String> program = new HashMap<>();
	private final List<Map<String, String>> programs = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addProgramBt = (Button) findViewById(R.id.add_new_program_bt);
		showAddPro = (TextView) findViewById(R.id.add_program_msg_tv);
		showMsgTv = (TextView) findViewById(R.id.show_program_run_msg_tv);
		addProgramBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_new_program_bt:
			program.put(ID, Integer.toString(++id));
			program.put(NAME, "Program" + id);
			program.put(CPU_TIME, Integer.toString(cpu_time));
			program.put(IO_TIME, Integer.toString(io_time));

			addPro = "Add New Program!\nName:" + program.get(NAME)
					+ "\nCPU Need " + program.get(CPU_TIME)
					+ " Seconds!\nI/O Need " + program.get(IO_TIME)
					+ " Seconds!\n\n" + addPro;
			programs.add(program);

			showAddPro.setText(addPro);

			for (int i = 0; i < programs.size(); i++) {
				runProgram();
			}

			break;
		}
	}

	private void runProgram() {

		for (int i = 0; i < programs.size(); i++) {
			Map<String, String> map = new HashMap<>();
			map = programs.get(i);
			if (Integer.valueOf(map.get(CPU_TIME)) <= 0) {
				runPro = "\n" + map.get(NAME) + "is Stoped\n" + runPro;
				programs.remove(i);

			} else {
				runPro = map.get(NAME) + " is Running...\nRemain "
						+ map.get(CPU_TIME) + "Seconds\n" + runPro;

				int cpu_time = Integer.valueOf(map.get(CPU_TIME)) - 5;
				map.put(CPU_TIME, Integer.toString(cpu_time));

			}
			showMsgTv.setText(runPro);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.clear_log_menu:
			showAddPro.setText("Nothing Here");
			showMsgTv.setText("Nothing Here");
			runPro = "";
			addPro = "";
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
