using UnityEngine;
using System.Collections;

public class FocusScript : MonoBehaviour {

    public GameObject baseLampara;
    private LlumScript mLlumScript;

    // Use this for initialization
    void Start () {
        mLlumScript = baseLampara.GetComponent<LlumScript>();

    }
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnTriggerEnter(Collider other)
    {
        mLlumScript.setXoc(true);
        //Destroy(other.gameObject);
    }
}
