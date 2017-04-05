using UnityEngine;
using System.Collections;

public class LlumScript : MonoBehaviour {

    public float VelocitatGir = 1;
    public float VelocitatAvans = 1;

    public float AlfaMax;
    public float BetaMax;


    public GameObject Llum;


	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {

        if(Input.GetButton("Jump"))
        {
            this.transform.RotateAround(transform.position, Vector3.up, VelocitatGir);
           // this.transform.Rotate(new Vector3(0, VelocitatGir, 0));

        }
        // h és +1 si anem a la dreta, i -1 si anem a l'esquerra.
        float h = Input.GetAxis("Horizontal");
        if (h!=0) {

            Debug.Log(this.transform.localRotation.eulerAngles.z);
            if ((this.transform.localRotation.eulerAngles.z > 20 && h < 0) ||
                (this.transform.localRotation.eulerAngles.z < 20  ))
            {

                this.transform.Rotate(new Vector3(0, 0, h * VelocitatGir));
            }

        }

        if (Llum!=null && Input.GetKeyUp(KeyCode.L))
        {
            Light li = Llum.GetComponent<Light>();
            li.enabled = !li.enabled;
        }

        float v = Input.GetAxis("Vertical");
        //this.transform.position = new Vector3(
        //                            transform.position.x + VelocitatAvans * v, 
        //                            transform.position.y, 
        //                            transform.position.z);

        //this.transform.position += new Vector3(
        //                            VelocitatAvans * v,
        //                            0,
        //                            0);

        // projectem el vector en el pla
        Vector3 direccioAvans = new Vector3(this.transform.right.x, 0, this.transform.right.z);
        // assegurem que tingui longitud 1
        direccioAvans.Normalize();
        this.transform.position += (-v*VelocitatAvans * direccioAvans);

        Debug.DrawRay(this.transform.position, this.transform.right,Color.red);
        Debug.DrawRay(this.transform.position, direccioAvans, Color.green);
        //
        // Debug.Log("HOLA MON");

    }
}
