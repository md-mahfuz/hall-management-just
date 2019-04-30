package just.cse.mahfuz.smrjust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

public class PendingAddStudentActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    PendingAddStudentRecyclerAdapter mPendingAddStudentRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_add_student);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        recyclerView=findViewById(R.id.recyclerView);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(PendingAddStudentActivity.this));



        Query query = firebaseFirestore.collection("pendingAddStudents").orderBy("time", Query.Direction.DESCENDING);



        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }
                List<PendingAddStudentModel> pendingAddStudentModels = queryDocumentSnapshots.toObjects(PendingAddStudentModel.class);

                mPendingAddStudentRecyclerAdapter = new PendingAddStudentRecyclerAdapter(PendingAddStudentActivity.this, pendingAddStudentModels);
                recyclerView.setAdapter(mPendingAddStudentRecyclerAdapter);
            }
        });





    }
}
