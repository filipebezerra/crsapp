package br.com.libertsolutions.crs.app.sync;

import android.content.Context;
import android.support.annotation.NonNull;
import br.com.libertsolutions.crs.app.sync.event.SyncType;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 05/06/2016
 * @since #
 */
class SyncManager {
    private Map<SyncType, AbstractSync> mSyncMap;

    SyncManager(@NonNull Context context) {
        mSyncMap = new HashMap<>();
        mSyncMap.put(SyncType.WORKS, new WorksSync(context));
        mSyncMap.put(SyncType.FLOWS, new FlowsSync(context));
        mSyncMap.put(SyncType.CHECKINS, new CheckinsSync(context));
    }

    void dispatchSync(@NonNull SyncType syncType) {
        if (syncType == SyncType.COMPLETE_SYNC) {
            Timber.i("SyncManager throwing works, flows sync");
            mSyncMap.get(SyncType.WORKS).sync();
            mSyncMap.get(SyncType.FLOWS).sync();
            //mSyncMap.get(SyncType.CHECKINS).sync();
        } else {
            mSyncMap.get(syncType).sync();
        }
    }
}
