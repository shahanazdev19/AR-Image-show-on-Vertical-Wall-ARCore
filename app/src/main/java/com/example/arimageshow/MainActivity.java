package com.example.arimageshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView  mRecycleView;
    private ListAdapter mAdapter;
    ListView listView;
    LinearLayout layout;
    RecyclerView.LayoutManager layoutManager;
    ArFragment arFragment;
    private Renderable  grassTreeRenderable,
                        riverTreeRenderable,
                        flowerRenderable,
                        nature1Renderable,
                        nature2Renderable,
                        nature3Renderable;
    ImageView grasstree,rivertree,flower,nature1,nature2,nature3;
    private List<ItemAdapter> mList = new ArrayList<>();
    View arrayView[];

    //private static final String TAG = ArCamera.class.getSimpleName();
    Session mSession;
    //private ArFragment arFragment;
    private ArSceneView arSceneView;
    private ModelRenderable modelRenderable;
    private boolean modelAdded = false;
    private boolean sessionConfigured = false;
    int selected=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment=(ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);
        arFragment.getPlaneDiscoveryController().hide();
        arFragment.getPlaneDiscoveryController().setInstructionView(null);

        arSceneView = arFragment.getArSceneView();
        //View
        grasstree=(ImageView) findViewById(R.id.grasstree1);
        rivertree=(ImageView) findViewById(R.id.rivertree1);
        flower=(ImageView) findViewById(R.id.flower);
        nature1=(ImageView) findViewById(R.id.nature1);
        nature2=(ImageView) findViewById(R.id.nature2);
        nature3=(ImageView) findViewById(R.id.nature3);
        //init();
        setArrayView();
        //adapter();
        setClickListener();
        setupModel();

        // set up the RecyclerView


        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                // When user tap on plane, we will add model
                    Anchor anchor=hitResult.createAnchor();
                    AnchorNode anchorNode=new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    createModel(arFragment,anchorNode,selected);
            }
        });
    }


    private void setupModel() {

         ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> grassTreeRenderable=renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> riverTreeRenderable=renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> flowerRenderable=renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> nature1Renderable=renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> nature2Renderable=renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.recyclerview_row)
                .build().thenAccept(renderable -> nature3Renderable=renderable);
       /*
        int imageResource2 = getResources().getIdentifier("@drawable/nature", null, getPackageName());

        ModelRenderable.builder()
                .setSource(this,imageResource2)
                .build().thenAccept(renderable->nature1Renderable=renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load nature 1 model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );*/


    }

    private void createModel(ArFragment fragment, AnchorNode anchorNode, int selected) {
        if(selected==1) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(grassTreeRenderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"Grass");
        }
        if(selected==2) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(riverTreeRenderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"River");
        }
        if(selected==3) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(flowerRenderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"flower");
        }
        if(selected==4) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(nature1Renderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"nature 1");
        }
        if(selected==5) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(nature2Renderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"Nature 2");
        }
        if(selected==6) {
            TransformableNode flower=new TransformableNode(arFragment.getTransformationSystem());
            flower.setParent(anchorNode);
            flower.setRenderable(nature3Renderable);
            //flower.setLocalRotation(Quaternion.axisAngle(new Vector3(-1f, 0, 0), 90f));
            //fragment.getArSceneView().getScene().addChild(anchorNode);
            flower.select();

            addName(anchorNode,flower,"Nature 3");
        }
    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {

        ViewRenderable.builder()
                .setView(this,R.layout.name_nature)
                .build()
                .thenAccept(viewRenderable-> {
                    TransformableNode imageName=new TransformableNode(arFragment.getTransformationSystem());
                    imageName.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+0.5f,0));
                    imageName.setParent(anchorNode);
                    imageName.setRenderable(viewRenderable);
                    imageName.select();

                    // set text
                    TextView txt_name=(TextView) viewRenderable.getView();
                    txt_name.setText(name);
                    // Click to text view to remove image
                    txt_name.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            anchorNode.setParent(null);
                        }
                    });

                });

    }

    private void setClickListener() {
        for(int i=0;i<mList.size();i++)
        {
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
       arrayView=new View[]{
               grasstree,rivertree,flower,nature1,nature2,nature3
        };
        /*ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.grasstree1);
        mList.add(itemAdapter);


        itemAdapter = new ItemAdapter();
        itemAdapter.setImage(R.drawable.rivertree1);
        mList.add(itemAdapter);*/
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.grasstree1){
            selected=1;
            setBackground(view.getId());
        }
        else if(view.getId()==R.id.rivertree1){
            selected=2;
            setBackground(view.getId());
        }
        else if(view.getId()==R.id.flower){
            selected=3;
            setBackground(view.getId());
        }
        else if(view.getId()==R.id.nature1){
            selected=4;
            setBackground(view.getId());
        }
        else if(view.getId()==R.id.nature2){
            selected=5;
            setBackground(view.getId());
        }
        else if(view.getId()==R.id.nature3){
            selected=6;
            setBackground(view.getId());
        }
    }

    private void setBackground(int id) {
        for (int i=0;i<arrayView.length;i++){
            if(arrayView[i].getId()==id)
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            else arrayView[i].setBackgroundColor(Color.TRANSPARENT);
        }

    }


}