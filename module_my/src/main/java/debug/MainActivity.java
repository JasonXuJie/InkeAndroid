package debug;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.jason.module_my.R;
import com.jason.module_my.mvp.ui.fragment.MyFragment;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

/**
 * Created by jason on 2018/11/14.
 */

public class MainActivity extends BaseActivity {


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl_container,new MyFragment());
        transaction.commit();

    }

    @Override
    public void requestData() {

    }
}
