package cl.vero.stressless.Data;

import java.util.ArrayList;
import java.util.List;
import cl.vero.stressless.models.Pending;


public class Queries {

    public List<Pending> pendings(){
        List<Pending> pendings = new ArrayList<>();
        List<Pending> pendingList = Pending.find(Pending.class,"done = 0");
        if (pendingList != null && pendingList.size() > 0){
            pendings.addAll(pendingList);
        }

        return pendings;
    }
}
