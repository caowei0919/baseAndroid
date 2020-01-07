package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcw.library.imagepicker.ImagePicker;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.FileUtils;
import com.zj.wz.wbyx.baseandroid.utils.GlideLoader;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.FullyLinearLayoutManager;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.LoadImageAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.ProblemAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.FeedBackPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.FeedBackView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import top.zibin.luban.Luban;

import static com.zj.wz.wbyx.baseandroid.config.Constant.REQUEST_OPEN_CAMERA;
import static com.zj.wz.wbyx.baseandroid.config.Constant.REQUEST_TAKE_PHOTO;

/**
 * FileName: FeedBackActivity
 * Author: 曹伟
 * Date: 2019/10/11 20:53
 * Description:问题反馈
 */

public class FeedBackActivity extends BaseMvpActivity<FeedBackView, FeedBackPresenter>
        implements FeedBackView , LoadImageAdapter.OnAddPictureClick {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;       //返回
    @BindView(R.id.tv_size)
    TextView tvSize ;
    @BindView(R.id.ed_feedback)
    EditText edFeedBack ;       //反馈输入框
    @BindView(R.id.tv_delete)
    TextView tvDelete   ;   //清除
    @BindView(R.id.recycle_loadImg)
    RecyclerView recycleLoadImg ;      //反馈提交的图片
    @BindView(R.id.tv_cellPhone)
    TextView tvCellPhone;       //热线电话
    @BindView(R.id.recycle_problem)
    RecyclerView recycleProblem ;       //常见问题

    private LoadImageAdapter mLoadImageAdapter ;    //选择图片适配器
    private List<String> imgUriString = new ArrayList<>();  //图片uri集合
    private Dialog dialogN ;
    private Uri imageUri ;  //拍照回调uri
    private File mTmpFile ;     //照片文件
    private List<ServiceBean.ListBean> problems = new ArrayList<>();
    private ProblemAdapter mProblemAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        //防止软键盘进页面就弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.feedback));

        //禁止输入框输入空格或者换行符
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start
                    , int end, Spanned dest, int dstart, int dend) {
                if(source.equals(" ") || source.toString().contentEquals("\n")){
                    return "";
                }
                return null;
            }
        };
        edFeedBack.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(500)});
        initRecycle();
        initGridRecycle();
    }

    private void initRecycle() {
        mProblemAdapter = new ProblemAdapter(mContext,problems);
        LinearLayoutManager layoutManager = new FullyLinearLayoutManager(mContext);
        //设置布局管理器
        recycleProblem.setLayoutManager(layoutManager);
        recycleProblem.setHasFixedSize(true);
        recycleProblem.setNestedScrollingEnabled(false);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleProblem.setAdapter(mProblemAdapter);
        recycleProblem.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,1
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));
        //设置增加或删除条目的动画
        recycleProblem.setItemAnimator( new DefaultItemAnimator());
    }

    /**
     * 初始化
     */
    private void initGridRecycle() {
        imgUriString.add("");
        recycleLoadImg.setLayoutManager(new GridLayoutManager(mContext,5));
        mLoadImageAdapter = new LoadImageAdapter(mContext,imgUriString,this) ;
        recycleLoadImg.setAdapter(mLoadImageAdapter);
    }

    /**
     * 反馈输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.ed_feedback,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onFeedBackTextChange(Editable editable){
        tvSize.setText(editable.toString().length() + "/500");
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getServerWrite(Constant.TYPE_WRITE);
    }

    @OnClick({R.id.linear_back,R.id.tv_delete,R.id.btn_commit})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                finish();
                break;

            case R.id.tv_delete :   //清除
                    edFeedBack.setText("");
                break;

            case R.id.btn_commit :  //提交
                List<String> files = new ArrayList<>();
                for (String path:imgUriString) {
                    if(!TextUtils.isEmpty(path)){
                        files.add(path);
                    }
                }
                try {
                    File[] imgFile = new File[Luban.with(mContext).load(files).get().size()];
                    for (int i =0;i<imgFile.length ;i++){
                        imgFile[i] = Luban.with(mContext).load(files).get().get(i).getAbsoluteFile();
                    }
                    getPresenter().submit(imgFile,edFeedBack.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 添加图片点击
     * @param position
     */
    @Override
    public void onAddPictureClick(int position) {
        showBottomDialog(mContext.getResources().getString(R.string.camera)
                ,mContext.getResources().getString(R.string.pick_for_album)
                ,5 - position);
    }

    private void showBottomDialog(String firstName , String secondName , int max) {
        //实例化对象
        dialogN = new AlertDialog.Builder(mContext).create();
        //显示dialog
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.BOTTOM);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.select_gender_dialog);
        TextView tvFirst =window.findViewById(R.id.tv_first);
        tvFirst.setText(firstName);
        TextView tvSecond =window.findViewById(R.id.tv_second);
        tvSecond.setText(secondName);
        TextView tvCancel =window.findViewById(R.id.tv_cancel);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeCamera();   //拍照
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
        tvSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum(max);    //相册
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
    }

    /**
     * 相册选取
     * @param max
     */
    private void openAlbum(int max) {
        ImagePicker.getInstance()
                .setTitle(mContext.getResources().getString(R.string.select_picture))//设置标题
                .setImageLoader(new GlideLoader())
                .showCamera(false)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(max)//设置最大选择图片数目(默认为1，单选)
                .start(mContext, REQUEST_OPEN_CAMERA);
    }

    /**
     * 拍照
     */
    private void takeCamera() {
        new RxPermissions(mContext)
                .request(Manifest.permission.CAMERA
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(RxUtils.applySchedulersLifeCycle(getMvpView()))
                .subscribe(new RxObserver<Boolean>(){
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean){      //授权通过拍摄照片
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (cameraIntent.resolveActivity(mContext.getPackageManager()) != null){
                                mTmpFile = FileUtils.createTmpFile(mContext);
                                //通过FileProvider创建一个content类型的Uri
                                imageUri = FileProvider.getUriForFile(mContext
                                        , "wbyx.wz.zj.com.fileprovider", mTmpFile);
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                mContext.startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
                            }
                        }else{
                            ToastUtils.showLongToast(mContext.getResources()
                                    .getString(R.string.dont_allow_permissions));
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO :   //拍照
                    imgUriString.remove(imgUriString.size() - 1);
                    imgUriString.add(mTmpFile.getPath());
                    if(imgUriString.size() < 5){
                        imgUriString.add("");
                    }
                    mLoadImageAdapter.notifyDataSetChanged();
                    break;

                default :  //相册选取
                    //将图片地址集合最后一个去除，再将选择的图片集合添加
                    imgUriString.remove(imgUriString.size() - 1);
                    imgUriString.addAll(data
                            .getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES));
                    if(imgUriString.size() < 5){
                        imgUriString.add("");
                    }
                    mLoadImageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    /**
     * 常见问题获取成功
     * @param response
     */
    @Override
    public void getServiceWriteSuccess(ServiceBean response) {
        String hotLine = mContext.getResources().getString(R.string.customer_service_hotline)
                + "<font color='#79ADE9'>" + response.getShow() + "</font>";
        tvCellPhone.setText(Html.fromHtml(hotLine));
        problems.addAll(response.getList());
        mProblemAdapter.notifyDataSetChanged();
    }

    /**
     * 意见反馈提交成功
     * @param baseBean
     */
    @Override
    public void getSubmitSuccess(BaseBean baseBean) {
        ToastUtils.showLongToast(baseBean.getMsg());
        finish();
    }
}
