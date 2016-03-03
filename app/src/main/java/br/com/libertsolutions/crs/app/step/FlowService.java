package br.com.libertsolutions.crs.app.step;

import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version 0.1.0, 03/03/2016
 * @since 0.1.0
 */
public interface FlowService {
    @GET("FluxoApi/Get")
    Observable<List<Flow>> getAll(int workId);
}