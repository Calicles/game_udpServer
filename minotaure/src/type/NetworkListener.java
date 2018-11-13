package type;

import model.NetEvent;
import model.TransferEvent;

public interface NetworkListener {
	
	void updateState(NetEvent ne);
	void update(TransferEvent te);

}
