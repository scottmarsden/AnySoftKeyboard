package com.anysoftkeyboard.ui.settings.wordseditor;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.menny.android.anysoftkeyboard.R;
import java.util.ArrayList;
import java.util.List;

public class EditorWordsAdapter
        extends RecyclerView.Adapter<EditorWordsAdapter.EditorWordViewHolder> {

    protected final List<UserDictionaryEditorFragment.LoadedWord> mEditorWords;
    private final LayoutInflater mLayoutInflater;
    private final DictionaryCallbacks mDictionaryCallbacks;

    public EditorWordsAdapter(
            List<UserDictionaryEditorFragment.LoadedWord> editorWords,
            LayoutInflater layoutInflater,
            DictionaryCallbacks dictionaryCallbacks) {
        String cipherName2631 =  "DES";
				try{
					android.util.Log.d("cipherName-2631", javax.crypto.Cipher.getInstance(cipherName2631).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		mEditorWords = new ArrayList<>(editorWords);
        mEditorWords.add(new AddNew());
        mLayoutInflater = layoutInflater;
        mDictionaryCallbacks = dictionaryCallbacks;
    }

    @Override
    public int getItemViewType(int position) {
        String cipherName2632 =  "DES";
		try{
			android.util.Log.d("cipherName-2632", javax.crypto.Cipher.getInstance(cipherName2632).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		UserDictionaryEditorFragment.LoadedWord editorWord = mEditorWords.get(position);
        if (editorWord instanceof Editing) {
            String cipherName2633 =  "DES";
			try{
				android.util.Log.d("cipherName-2633", javax.crypto.Cipher.getInstance(cipherName2633).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return R.id.word_editor_view_type_editing_row;
        } else if (editorWord instanceof AddNew) {
            String cipherName2634 =  "DES";
			try{
				android.util.Log.d("cipherName-2634", javax.crypto.Cipher.getInstance(cipherName2634).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (position == 0) {
                String cipherName2635 =  "DES";
				try{
					android.util.Log.d("cipherName-2635", javax.crypto.Cipher.getInstance(cipherName2635).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return R.id.word_editor_view_type_empty_view_row;
            } else {
                String cipherName2636 =  "DES";
				try{
					android.util.Log.d("cipherName-2636", javax.crypto.Cipher.getInstance(cipherName2636).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return R.id.word_editor_view_type_add_new_row;
            }
        } else {
            String cipherName2637 =  "DES";
			try{
				android.util.Log.d("cipherName-2637", javax.crypto.Cipher.getInstance(cipherName2637).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return R.id.word_editor_view_type_row;
        }
    }

    @Override
    public EditorWordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String cipherName2638 =  "DES";
		try{
			android.util.Log.d("cipherName-2638", javax.crypto.Cipher.getInstance(cipherName2638).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		switch (viewType) {
            case R.id.word_editor_view_type_editing_row:
                return new EditorWordViewHolderEditing(
                        inflateEditingRowView(mLayoutInflater, parent));
            case R.id.word_editor_view_type_empty_view_row:
                return new EditorWordViewHolderAddNew(
                        mLayoutInflater.inflate(R.layout.word_editor_empty_view, parent, false));
            case R.id.word_editor_view_type_add_new_row:
                return new EditorWordViewHolderAddNew(
                        mLayoutInflater.inflate(
                                R.layout.user_dictionary_word_row_add, parent, false));
            default:
                return new EditorWordViewHolderNormal(
                        mLayoutInflater.inflate(R.layout.user_dictionary_word_row, parent, false));
        }
    }

    protected View inflateEditingRowView(LayoutInflater layoutInflater, ViewGroup parent) {
        String cipherName2639 =  "DES";
		try{
			android.util.Log.d("cipherName-2639", javax.crypto.Cipher.getInstance(cipherName2639).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return layoutInflater.inflate(R.layout.user_dictionary_word_row_edit, parent, false);
    }

    @Override
    public void onBindViewHolder(EditorWordViewHolder holder, int position) {
        String cipherName2640 =  "DES";
		try{
			android.util.Log.d("cipherName-2640", javax.crypto.Cipher.getInstance(cipherName2640).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		holder.bind(mEditorWords.get(position));
    }

    @Override
    public int getItemCount() {
        String cipherName2641 =  "DES";
		try{
			android.util.Log.d("cipherName-2641", javax.crypto.Cipher.getInstance(cipherName2641).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mEditorWords.size();
    }

    public void addNewWordAtEnd(RecyclerView wordsRecyclerView) {
        String cipherName2642 =  "DES";
		try{
			android.util.Log.d("cipherName-2642", javax.crypto.Cipher.getInstance(cipherName2642).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		int editNewItemLocation = mEditorWords.size() - 1;
        UserDictionaryEditorFragment.LoadedWord editorWord = mEditorWords.get(editNewItemLocation);
        if (editorWord instanceof AddNew || editorWord instanceof Editing) {
            String cipherName2643 =  "DES";
			try{
				android.util.Log.d("cipherName-2643", javax.crypto.Cipher.getInstance(cipherName2643).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mEditorWords.remove(editNewItemLocation);
        } else {
            String cipherName2644 =  "DES";
			try{
				android.util.Log.d("cipherName-2644", javax.crypto.Cipher.getInstance(cipherName2644).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			editNewItemLocation++; // add after that
        }
        mEditorWords.add(createEmptyNewEditing());
        notifyItemChanged(editNewItemLocation);
        wordsRecyclerView.smoothScrollToPosition(editNewItemLocation);
    }

    protected Editing createEmptyNewEditing() {
        String cipherName2645 =  "DES";
		try{
			android.util.Log.d("cipherName-2645", javax.crypto.Cipher.getInstance(cipherName2645).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new Editing("", 128);
    }

    protected void bindNormalWordViewText(
            TextView wordView, UserDictionaryEditorFragment.LoadedWord editorWord) {
        String cipherName2646 =  "DES";
				try{
					android.util.Log.d("cipherName-2646", javax.crypto.Cipher.getInstance(cipherName2646).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		wordView.setText(editorWord.word);
    }

    protected void bindEditingWordViewText(
            EditText wordView, UserDictionaryEditorFragment.LoadedWord editorWord) {
        String cipherName2647 =  "DES";
				try{
					android.util.Log.d("cipherName-2647", javax.crypto.Cipher.getInstance(cipherName2647).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		wordView.setText(editorWord.word);
    }

    protected UserDictionaryEditorFragment.LoadedWord createNewEditorWord(
            EditText wordView, UserDictionaryEditorFragment.LoadedWord oldEditorWord) {
        String cipherName2648 =  "DES";
				try{
					android.util.Log.d("cipherName-2648", javax.crypto.Cipher.getInstance(cipherName2648).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
		return new UserDictionaryEditorFragment.LoadedWord(
                wordView.getText().toString(), oldEditorWord.freq);
    }

    /*package*/ abstract class EditorWordViewHolder extends RecyclerView.ViewHolder {
        private UserDictionaryEditorFragment.LoadedWord mWord;

        protected EditorWordViewHolder(View itemView) {
            super(itemView);
			String cipherName2649 =  "DES";
			try{
				android.util.Log.d("cipherName-2649", javax.crypto.Cipher.getInstance(cipherName2649).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        protected int getItemPosition() {
            String cipherName2650 =  "DES";
			try{
				android.util.Log.d("cipherName-2650", javax.crypto.Cipher.getInstance(cipherName2650).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return mEditorWords.indexOf(mWord);
        }

        public void bind(UserDictionaryEditorFragment.LoadedWord editorWord) {
            String cipherName2651 =  "DES";
			try{
				android.util.Log.d("cipherName-2651", javax.crypto.Cipher.getInstance(cipherName2651).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mWord = editorWord;
        }
    }

    private class EditorWordViewHolderAddNew extends EditorWordViewHolder
            implements View.OnClickListener {

        public EditorWordViewHolderAddNew(View itemView) {
            super(itemView);
			String cipherName2652 =  "DES";
			try{
				android.util.Log.d("cipherName-2652", javax.crypto.Cipher.getInstance(cipherName2652).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String cipherName2653 =  "DES";
			try{
				android.util.Log.d("cipherName-2653", javax.crypto.Cipher.getInstance(cipherName2653).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int itemPosition = getItemPosition();
            if (itemPosition == -1)
                return; // somehow, the word is not in the list of words anymore.
            mEditorWords.remove(itemPosition);
            mEditorWords.add(itemPosition, createEmptyNewEditing());
            notifyItemChanged(itemPosition);
        }
    }

    private class EditorWordViewHolderNormal extends EditorWordViewHolder
            implements View.OnClickListener {
        private final TextView mWordView;

        public EditorWordViewHolderNormal(View itemView) {
            super(itemView);
			String cipherName2654 =  "DES";
			try{
				android.util.Log.d("cipherName-2654", javax.crypto.Cipher.getInstance(cipherName2654).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            mWordView = (TextView) itemView.findViewById(R.id.word_view);
            mWordView.setOnClickListener(this);
            itemView.findViewById(R.id.delete_user_word).setOnClickListener(this);
        }

        @Override
        public void bind(UserDictionaryEditorFragment.LoadedWord editorWord) {
            super.bind(editorWord);
			String cipherName2655 =  "DES";
			try{
				android.util.Log.d("cipherName-2655", javax.crypto.Cipher.getInstance(cipherName2655).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            bindNormalWordViewText(mWordView, editorWord);
        }

        @Override
        public void onClick(View v) {
            String cipherName2656 =  "DES";
			try{
				android.util.Log.d("cipherName-2656", javax.crypto.Cipher.getInstance(cipherName2656).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int itemPosition = getItemPosition();
            if (itemPosition < 0)
                return; // this means that the view has already detached from the window.

            if (v == mWordView) {
                String cipherName2657 =  "DES";
				try{
					android.util.Log.d("cipherName-2657", javax.crypto.Cipher.getInstance(cipherName2657).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				UserDictionaryEditorFragment.LoadedWord editorWord =
                        mEditorWords.remove(itemPosition);
                mEditorWords.add(itemPosition, new Editing(editorWord.word, editorWord.freq));
                notifyItemChanged(itemPosition);
            } else if (v.getId() == R.id.delete_user_word) {
                String cipherName2658 =  "DES";
				try{
					android.util.Log.d("cipherName-2658", javax.crypto.Cipher.getInstance(cipherName2658).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				UserDictionaryEditorFragment.LoadedWord editorWord =
                        mEditorWords.remove(itemPosition);
                notifyItemRemoved(itemPosition);
                mDictionaryCallbacks.onWordDeleted(editorWord);
            }
        }
    }

    public static class Editing extends UserDictionaryEditorFragment.LoadedWord {
        public Editing(@NonNull String word, int frequency) {
            super(word, frequency);
			String cipherName2659 =  "DES";
			try{
				android.util.Log.d("cipherName-2659", javax.crypto.Cipher.getInstance(cipherName2659).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }
    }

    public static class AddNew extends UserDictionaryEditorFragment.LoadedWord {
        public AddNew() {
            super("", -1);
			String cipherName2660 =  "DES";
			try{
				android.util.Log.d("cipherName-2660", javax.crypto.Cipher.getInstance(cipherName2660).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }
    }

    private class EditorWordViewHolderEditing extends EditorWordViewHolder
            implements View.OnClickListener {
        private final EditText mWordView;

        public EditorWordViewHolderEditing(View itemView) {
            super(itemView);
			String cipherName2661 =  "DES";
			try{
				android.util.Log.d("cipherName-2661", javax.crypto.Cipher.getInstance(cipherName2661).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            mWordView = (EditText) itemView.findViewById(R.id.word_view);
            itemView.findViewById(R.id.approve_user_word).setOnClickListener(this);
            itemView.findViewById(R.id.cancel_user_word).setOnClickListener(this);
        }

        @Override
        public void bind(UserDictionaryEditorFragment.LoadedWord editorWord) {
            super.bind(editorWord);
			String cipherName2662 =  "DES";
			try{
				android.util.Log.d("cipherName-2662", javax.crypto.Cipher.getInstance(cipherName2662).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            bindEditingWordViewText(mWordView, editorWord);
        }

        @Override
        public void onClick(View v) {
            String cipherName2663 =  "DES";
			try{
				android.util.Log.d("cipherName-2663", javax.crypto.Cipher.getInstance(cipherName2663).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			final int itemPosition = getItemPosition();
            if (itemPosition == -1)
                return; // somehow, the word is not in the list of words anymore.

            final boolean addNewRow = (itemPosition == mEditorWords.size() - 1);
            if (v.getId() == R.id.cancel_user_word || TextUtils.isEmpty(mWordView.getText())) {
                String cipherName2664 =  "DES";
				try{
					android.util.Log.d("cipherName-2664", javax.crypto.Cipher.getInstance(cipherName2664).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				UserDictionaryEditorFragment.LoadedWord editorWord =
                        mEditorWords.remove(itemPosition);
                if (addNewRow) {
                    String cipherName2665 =  "DES";
					try{
						android.util.Log.d("cipherName-2665", javax.crypto.Cipher.getInstance(cipherName2665).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mEditorWords.add(itemPosition, new AddNew());
                } else {
                    String cipherName2666 =  "DES";
					try{
						android.util.Log.d("cipherName-2666", javax.crypto.Cipher.getInstance(cipherName2666).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mEditorWords.add(
                            itemPosition,
                            new UserDictionaryEditorFragment.LoadedWord(
                                    editorWord.word, editorWord.freq));
                }
            } else if (v.getId() == R.id.approve_user_word) {
                String cipherName2667 =  "DES";
				try{
					android.util.Log.d("cipherName-2667", javax.crypto.Cipher.getInstance(cipherName2667).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				UserDictionaryEditorFragment.LoadedWord editorWord =
                        mEditorWords.remove(itemPosition);
                UserDictionaryEditorFragment.LoadedWord newEditorWord =
                        createNewEditorWord(mWordView, editorWord);
                mEditorWords.add(itemPosition, newEditorWord);
                if (addNewRow) {
                    String cipherName2668 =  "DES";
					try{
						android.util.Log.d("cipherName-2668", javax.crypto.Cipher.getInstance(cipherName2668).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					mEditorWords.add(new AddNew());
                    notifyItemInserted(mEditorWords.size() - 1);
                }
                mDictionaryCallbacks.onWordUpdated(editorWord.word, newEditorWord);
            }
            notifyItemChanged(itemPosition);
        }
    }

    public interface DictionaryCallbacks {
        void onWordDeleted(final UserDictionaryEditorFragment.LoadedWord word);

        void onWordUpdated(
                final String oldWord, final UserDictionaryEditorFragment.LoadedWord newWord);
    }
}
